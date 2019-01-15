package io.github.ginger.schedule.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.ginger.schedule.domain.model.Block


@Database(entities = [Block::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class AgendaDatabase : RoomDatabase() {
  abstract fun agendaDao(): AgendaDao
}