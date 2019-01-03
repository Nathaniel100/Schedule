package io.github.ginger.schedule.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
  viewModelFactory: ViewModelProvider.Factory
) = ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)

// Returns ViewModel
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
  viewModelFactory: ViewModelProvider.Factory
) = ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)

// Returns ViewModel scoped to the Activity
inline fun <reified VM : ViewModel> Fragment.activityViewModel(
  viewModelFactory: ViewModelProvider.Factory
) = ViewModelProviders.of(requireActivity(), viewModelFactory).get(VM::class.java)

fun <T, R> LiveData<T>.map(body: (T) -> R): LiveData<R> {
  return Transformations.map(this, body)
}

suspend fun <R> safeApiCall(call: suspend () -> R): Result<R> {
  return try {
    val r = call()
    Result.Success(r)
  } catch (e: Exception) {
    Result.Error(e)
  }
}