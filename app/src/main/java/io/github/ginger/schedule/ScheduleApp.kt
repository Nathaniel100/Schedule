package io.github.ginger.schedule

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.ginger.schedule.di.DaggerAppComponent
import timber.log.Timber


class ScheduleApp : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
    // Timber
    Timber.plant(Timber.DebugTree())
    // Threeten
    AndroidThreeTen.init(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().create(this)
  }

}