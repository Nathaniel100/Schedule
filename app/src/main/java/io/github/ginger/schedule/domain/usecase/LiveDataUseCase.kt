package io.github.ginger.schedule.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.github.ginger.schedule.util.Result

abstract class LiveDataUseCase<in P, R> {

  protected val liveResult = MediatorLiveData<Result<R>>()

  fun observe(): LiveData<Result<R>> = liveResult

  abstract suspend operator fun invoke(parameters: P)
}
