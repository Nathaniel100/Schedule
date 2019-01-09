package io.github.ginger.schedule.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.ginger.schedule.domain.model.Block


@Dao
interface AgendaDao {

  @Query("SELECT * FROM agenda")
  fun getAgendaItems(): LiveData<List<Block>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAgendaItems(agenda: List<Block>)

  @Delete
  fun deleteAgendaItem(item: Block)

  @Query("DELETE FROM agenda")
  fun clear()
}