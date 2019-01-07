package io.github.ginger.schedule.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.domain.usecase.LoadAgendaUseCase
import io.github.ginger.schedule.util.Result
import io.github.ginger.schedule.util.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZoneId
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class ScheduleViewModel @Inject constructor(
  private val loadAgendaUseCase: LoadAgendaUseCase,
  @Named("IO") private val io: CoroutineContext,
  @Named("UI") private val ui: CoroutineContext
) : ViewModel() {

  private val loadAgendaResult = MutableLiveData<Result<List<Block>>>()
  val agenda: LiveData<List<Block>>
  val timeZoneId: ZoneId = ZoneId.systemDefault()

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
}