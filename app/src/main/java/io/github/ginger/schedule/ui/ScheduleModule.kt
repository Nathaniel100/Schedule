package io.github.ginger.schedule.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.ginger.schedule.di.scope.FragmentScoped
import io.github.ginger.schedule.ui.schedule.ScheduleFragment

@Module
abstract class ScheduleModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun scheduleFragment(): ScheduleFragment
}