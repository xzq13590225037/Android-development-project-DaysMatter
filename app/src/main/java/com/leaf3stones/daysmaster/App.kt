package com.leaf3stones.daysmaster

import android.app.Application
import android.content.Context
import com.leaf3stones.daysmaster.data.database.AppDatabaseProvider
import org.xutils.x

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        x.Ext.init(this)

        AppDatabaseProvider.initDatabase(this)
    }

    companion object {
        lateinit var appContext: Context
    }
}