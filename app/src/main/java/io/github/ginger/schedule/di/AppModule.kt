package io.github.ginger.schedule.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.ginger.schedule.ScheduleApp
import io.github.ginger.schedule.repository.db.AgendaDatabase
import kotlinx.coroutines.Dispatchers
import org.threeten.bp.ZoneId
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {
  @Provides
  fun provideContext(application: ScheduleApp): Context = application.applicationContext

  @Singleton
  @Provides
  @Named("IO")
  fun provideIoCoroutineContext(): CoroutineContext = Dispatchers.IO

  @Singleton
  @Provides
  @Named("UI")
  fun provideUiCoroutineContext(): CoroutineContext = Dispatchers.Main

  @Singleton
  @Provides
  fun provideAgendaDb(applicationContext: Context): AgendaDatabase =
    Room.databaseBuilder(applicationContext, AgendaDatabase::class.java, "agenda.db")
      .fallbackToDestructiveMigration()
      .build()

  @Singleton
  @Provides
  fun provideZoneId(): ZoneId = ZoneId.systemDefault()

}