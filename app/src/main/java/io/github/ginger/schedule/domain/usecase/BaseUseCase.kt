package io.github.ginger.schedule.domain.usecase

import io.github.ginger.schedule.util.Result
import io.github.ginger.schedule.util.safeApiCall

abstract class BaseUseCase<in P, R> {

  suspend operator fun invoke(parameters: P): Result<R> {
    return safeApiCall {
      execute(parameters)
    }
  }

  abstract suspend fun execute(parameters: P): R
}
