package io.github.ginger.schedule.util

sealed class Result<out R> {
  class Success<out T>(val data: T) : Result<T>()
  class Error(val exception: Exception) : Result<Nothing>()
  object Loading : Result<Nothing>()

  override fun toString(): String {
    return when (this) {
      is Success<*> -> "Success[data=$data]"
      is Error -> "Error[exception=$exception]"
      Loading -> "Loading"
    }
  }
}

val Result<*>.succeeded
  get() = this is Result.Success && data != null