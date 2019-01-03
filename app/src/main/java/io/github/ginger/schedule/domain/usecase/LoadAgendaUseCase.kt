package io.github.ginger.schedule.domain.usecase

import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.repository.AgendaRepository
import javax.inject.Inject

open class LoadAgendaUseCase @Inject constructor(private val agendaRepository: AgendaRepository) :
  BaseUseCase<Unit, List<Block>>() {
  override suspend fun execute(parameters: Unit): List<Block> {
    return agendaRepository.getAgenda()
  }
}