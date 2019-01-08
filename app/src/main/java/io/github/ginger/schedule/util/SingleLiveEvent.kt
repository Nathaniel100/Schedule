package io.github.ginger.schedule.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T>() {

  private val handled = AtomicBoolean(false)

  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
    super.observe(owner, Observer { t ->
      if (handled.compareAndSet(true, false)) {
        observer.onChanged(t)
      }
    })
  }

  override fun setValue(value: T?) {
    handled.set(true)
    super.setValue(value)
  }

  fun call() {
    this.value = null
  }
}