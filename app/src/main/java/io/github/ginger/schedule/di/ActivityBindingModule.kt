package io.github.ginger.schedule.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.ginger.schedule.di.scope.ActivityScoped
import io.github.ginger.schedule.ui.MainActivity
import io.github.ginger.schedule.ui.ScheduleModule

@Module
abstract class ActivityBindingModule {

  @ActivityScoped
  @ContributesAndroidInjector(modules = [ScheduleModule::class])
  abstract fun mainActivity(): MainActivity
}