package io.github.ginger.schedule.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.ginger.schedule.di.scope.ActivityScoped
import io.github.ginger.schedule.ui.schedule.ScheduleActivity
import io.github.ginger.schedule.ui.schedule.ScheduleModule

@Module
abstract class ActivityBindingModule {

  @ActivityScoped
  @ContributesAndroidInjector(modules = [ScheduleModule::class])
  abstract fun mainActivity(): ScheduleActivity
}