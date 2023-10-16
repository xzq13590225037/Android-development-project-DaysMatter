package com.leaf3stones.daysmaster.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.leaf3stones.daysmaster.data.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM EVENTS WHERE eventId = :id")
    fun getEventById(id: Int): Flow<Event>

    @Query("SELECT * FROM EVENTS WHERE eventId = :id")
    fun getEventByIdBlocking(id: Int): Event


    @Query("SELECT * FROM EVENTS ORDER BY date DESC LIMIT 1")
    fun getTopEvent(): Flow<Event>

    @Query("SELECT * FROM EVENTS WHERE showAtTop = 1 ORDER BY date DESC")
    fun getAllShowHeadEvents(): Flow<List<Event>>


    @Query("SELECT * FROM EVENTS  WHERE showAtTop = 0 ORDER BY date DESC")
    fun getAllShowEndEvents(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEvent(event: Event)

    @Update
    fun updateEvent(event: Event)
}