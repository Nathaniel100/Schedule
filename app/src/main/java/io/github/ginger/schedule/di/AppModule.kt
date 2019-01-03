package io.github.ginger.schedule.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {
  @Provides
  fun provideContext(application: Application): Context = application.applicationContext

  @Singleton
  @Provides
  @Named("IO")
  fun provideIoCoroutineContext(): CoroutineContext = Dispatchers.IO

  @Singleton
  @Provides
  @Named("UI")
  fun provideUiCoroutineContext(): CoroutineContext = Dispatchers.Main

}