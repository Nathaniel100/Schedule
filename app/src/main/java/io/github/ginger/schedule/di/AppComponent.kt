package io.github.ginger.schedule.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.ginger.schedule.ScheduleApp
import javax.inject.Singleton


@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBindingModule::class,
    ViewModelModule::class
  ]
)
interface AppComponent : AndroidInjector<ScheduleApp> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<ScheduleApp>()
}
