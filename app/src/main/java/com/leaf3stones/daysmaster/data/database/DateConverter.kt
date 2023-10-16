package com.leaf3stones.daysmaster.data.database

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }
}