package io.github.ginger.schedule.ui.schedule

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.ginger.schedule.BR
import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.domain.usecase.AddAgendaUseCase
import io.github.ginger.schedule.domain.usecase.LoadAgendaUseCase
import io.github.ginger.schedule.util.Result
import io.github.ginger.schedule.util.SingleLiveEvent
import io.github.ginger.schedule.util.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.DateTimeException
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class AddAgendaModel : BaseObservable() {

  @get:Bindable
  var title: String = ""
    set(value) {
      field = value
      notifyPropertyChanged(BR.title)
    }

  @get:Bindable
  var startTime: String = ""
    set(value) {
      field = value
      notifyPropertyChanged(BR.startTime)
    }

  @get:Bindable
  var endTime: String = ""
    set(value) {
      field = value
      notifyPropertyChanged(BR.endTime)
    }

  @get:Bindable
  var type: String = "meal"
    set(value) {
      field = value
      notifyChange()
    }
}

class ScheduleViewModel @Inject constructor(
  val timeZoneId: ZoneId,
  private val loadAgendaUseCase: LoadAgendaUseCase,
  private val addAgendaUseCase: AddAgendaUseCase,
  @Named("IO") private val io: CoroutineContext,
  @Named("UI") private val ui: CoroutineContext
) : ViewModel() {

  private val formatter =
    DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .appendValue(ChronoField.YEAR, 4)
      .appendLiteral("-")
      .appendValue(ChronoField.MONTH_OF_YEAR, 2)
      .appendLiteral("-")
      .appendValue(ChronoField.DAY_OF_MONTH, 2)
      .appendLiteral(" ")
      .appendValue(ChronoField.HOUR_OF_DAY, 2)
      .appendLiteral(":")
      .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
      .appendLiteral(":")
      .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
      .toFormatter()
  val agenda: LiveData<List<Block>> = loadAgendaUseCase.observe().map {
    (it as? Result.Success)?.data ?: emptyList()
  }
  val backEvent = SingleLiveEvent<Void>()
  val errorEvent = SingleLiveEvent<Exception>()
  val addAgendaModel = AddAgendaModel()

  private val title: String
    get() = addAgendaModel.title
  private val startTime: ZonedDateTime
    get() = ZonedDateTime.parse(addAgendaModel.startTime, formatter.withZone(timeZoneId))
  private val endTime: ZonedDateTime
    get() = ZonedDateTime.parse(addAgendaModel.endTime, formatter.withZone(timeZoneId))
  private val type: String
    get() = if (addAgendaModel.type.isEmpty()) "session" else addAgendaModel.type
  private val color: Int
    get() = when (type) {
      "after_hours" -> 0xff202124.toInt()
      "badge" -> 0xffe6e6e6.toInt()
      "codelab" -> 0xff4768fd.toInt()
      "concert" -> 0xff202124.toInt()
      "keynote" -> 0xfffcd230.toInt()
      "meal" -> 0xff31e7b6.toInt()
      "office_hours" -> 0xff4768fd.toInt()
      "sandbox" -> 0xff4768fd.toInt()
      "store" -> 0xffffffff.toInt()
      else -> 0xff27e5fd.toInt()
    }
  private val isDark: Boolean
    get() = when (type) {
      "after_hours" -> false
      "badge" -> false
      "codelab" -> true
      "concert" -> false
      "keynote" -> false
      "meal" -> false
      "office_hours" -> true
      "sandbox" -> true
      "store" -> false
      else -> true
    }

  init {
    CoroutineScope(ui).launch {
      loadAgendaUseCase(Unit)
    }
  }

  fun addAgenda() {
    CoroutineScope(ui).launch {
      val block = createBlock() ?: return@launch
      withContext(io) {
        addAgendaUseCase(block)
      }
      backEvent.call()
    }
  }

  private fun createBlock(): Block? {
    return try {
      Block(
        title = title,
        type = type,
        color = color,
        isDark = isDark,
        startTime = startTime,
        endTime = endTime
      )
    } catch (e: DateTimeException) {
      errorEvent.value = e
      null
    }

  }
}