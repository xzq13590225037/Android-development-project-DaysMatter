package com.leaf3stones.daysmaster.base

import android.app.Application
import org.xutils.x


/**
 * 初始化程序
 */
class UniteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // 初始化
        x.Ext.init(this)
        //x.Ext.setDebug(true); // 是否输出debug日志...
    }
}