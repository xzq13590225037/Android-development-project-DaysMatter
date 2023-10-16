package com.leaf3stones.daysmaster.listener

import com.leaf3stones.daysmaster.entities.Note

interface NotesListener {
    fun onNoteClicked(note: Note, position: Int)
}