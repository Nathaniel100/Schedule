package io.github.ginger.schedule.ui.schedule

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.ginger.schedule.di.ViewModelKey
import io.github.ginger.schedule.di.scope.FragmentScoped

@Module
abstract class ScheduleModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun scheduleFragment(): ScheduleFragment

  @Binds
  @IntoMap
  @ViewModelKey(ScheduleViewModel::class)
  abstract fun bindScheduleViewModel(viewModel: ScheduleViewModel): ViewModel
}