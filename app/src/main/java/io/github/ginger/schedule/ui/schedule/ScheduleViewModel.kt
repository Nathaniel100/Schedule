package io.github.ginger.schedule.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class ScheduleViewModel @Inject constructor(
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
  private val loadAgendaResult = MutableLiveData<Result<List<Block>>>()
  val agenda: LiveData<List<Block>>
  val timeZoneId: ZoneId = ZoneId.systemDefault()
  val title = MutableLiveData<String>()
  val startTime = MutableLiveData<String>()
  val endTime = MutableLiveData<String>()
  val backEvent = SingleLiveEvent<Void>()

  init {
    agenda = loadAgendaResult.map {
      (it as? Result.Success)?.data ?: emptyList()
    }
    CoroutineScope(ui).launch {
      loadAgendaResult.value = withContext(io) {
        loadAgendaUseCase(Unit)
      }
    }
  }

  fun addAgenda() {
    CoroutineScope(ui).launch {
      val block = createBlock() ?: return@launch
      loadAgendaResult.value = withContext(io) {
        addAgendaUseCase(block)
      }
      backEvent.call()
    }
  }

  // TODO createBlock
  private fun createBlock(): Block? {
    return try {
      Block(
        title = title.value.orEmpty(),
        type = "meal",
        color = 0xff31e7b6.toInt(),
        startTime = ZonedDateTime.parse(startTime.value, formatter.withZone(timeZoneId)),
        endTime = ZonedDateTime.parse(endTime.value, formatter.withZone(timeZoneId))
      )
    } catch (e: DateTimeException) {
      Timber.e(e)
      null
    }

  }
}