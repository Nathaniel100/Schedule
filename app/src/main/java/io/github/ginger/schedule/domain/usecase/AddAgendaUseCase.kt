package io.github.ginger.schedule.domain.usecase

import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.repository.AgendaRepository
import javax.inject.Inject

open class AddAgendaUseCase @Inject constructor(
  private val repository: AgendaRepository
) : BaseUseCase<Block, Unit>() {

  override suspend fun execute(parameters: Block) {
    repository.insertAgendaItems(listOf(parameters))
  }

}