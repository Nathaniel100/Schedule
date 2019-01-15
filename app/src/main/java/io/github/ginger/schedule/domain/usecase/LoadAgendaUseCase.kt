package io.github.ginger.schedule.domain.usecase

import androidx.annotation.MainThread
import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.repository.AgendaRepository
import io.github.ginger.schedule.util.Result
import javax.inject.Inject

open class LoadAgendaUseCase @Inject constructor(
  private val agendaRepository: AgendaRepository
) : LiveDataUseCase<Unit, List<Block>>() {

  @MainThread
  override suspend fun invoke(parameters: Unit) {
    liveResult.addSource(agendaRepository.getAgenda()) {
      liveResult.postValue(Result.Success(it))
    }
  }


}