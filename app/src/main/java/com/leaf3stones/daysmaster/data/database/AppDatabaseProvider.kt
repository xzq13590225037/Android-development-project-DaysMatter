package com.leaf3stones.daysmaster.data.database

import android.content.Context
import androidx.room.Room
import com.leaf3stones.daysmaster.App

class AppDatabaseProvider {

    companion object {
        var appDatabase: AppDatabase? = null

        fun initDatabase(context: Context) {
            appDatabase = Room.databaseBuilder(
               context, AppDatabase::class.java, "app.db"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}