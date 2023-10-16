package com.leaf3stones.daysmaster.Dao

import androidx.room.*
import com.leaf3stones.daysmaster.entities.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note?)

    @Delete
    fun deleteNote(note: Note?)
}