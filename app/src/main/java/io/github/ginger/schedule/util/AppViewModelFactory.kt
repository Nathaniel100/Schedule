package io.github.ginger.schedule.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class AppViewModelFactory @Inject constructor(
  private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    val found = creators.entries.find {
      modelClass.isAssignableFrom(it.key)
    }
    val creator = found?.value ?: throw IllegalArgumentException("Invalid modelClass: $modelClass")
    try {
      return creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}