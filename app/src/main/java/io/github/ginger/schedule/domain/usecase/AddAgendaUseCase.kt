package io.github.ginger.schedule.domain.usecase

import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.repository.AgendaRepository
import javax.inject.Inject

class AddAgendaUseCase @Inject constructor(
  private val repository: AgendaRepository
) : BaseUseCase<Block, List<Block>>() {
  override suspend fun execute(parameters: Block): List<Block> {
    repository.insertAgendaItems(listOf(parameters))
    return repository.getAgenda()
  }
}