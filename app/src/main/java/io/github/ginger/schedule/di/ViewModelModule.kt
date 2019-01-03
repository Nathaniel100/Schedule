package io.github.ginger.schedule.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.github.ginger.schedule.util.AppViewModelFactory

@Module
abstract class ViewModelModule {
  @Binds
  abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}