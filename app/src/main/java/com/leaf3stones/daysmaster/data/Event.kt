package com.leaf3stones.daysmaster.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val eventId: Int = 0,
    val eventName: String,
    val date: Date,
    val category: String = "default",
    val showAtTop: Boolean = false,
    val addOneDay: Boolean = false,
    val remindRegularly: Boolean = false
)
