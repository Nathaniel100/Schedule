package io.github.ginger.schedule.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.domain.model.BlockData
import javax.inject.Singleton

@Module
class AppModule {
  @Provides
  fun provideContext(application: Application): Context = application.applicationContext

  @Singleton
  @Provides
  fun provideBlocks(): Array<Block> = BlockData.blocks
}