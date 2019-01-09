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
  val title = MutableLiveData<String>()
  val startTime = MutableLiveData<String>()
  val endTime = MutableLiveData<String>()
  val backEvent = SingleLiveEvent<Void>()
  val errorEvent = SingleLiveEvent<Exception>()

  init {
    CoroutineScope(io).launch {
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
      errorEvent.value = e
      null
    }

  }
}