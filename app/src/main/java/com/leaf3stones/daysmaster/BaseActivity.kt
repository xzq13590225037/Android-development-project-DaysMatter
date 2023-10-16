package com.leaf3stones.daysmaster

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    private lateinit var text: TextReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("BaseActivity", javaClass.simpleName)
        //添加activity
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        text = TextReceiver()
        val textFilter = IntentFilter("com.leaf3stones.daysmaster.TextReceiver")
        registerReceiver(text, textFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(text)
    }

    inner class TextReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            AlertDialog.Builder(context)
                .setTitle("警告")
                .setMessage("登录失败次数超过3次！ 退出登录界面")
                .setPositiveButton("确定") { _, _ ->
                    ActivityCollector.finishAll()
                }
                .setNeutralButton("取消", null)
                .create()
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //销毁activity
        ActivityCollector.removeActivity(this)
    }
}

object ActivityCollector {

    private val activityList = ArrayList<Activity>()

    //添加activity到集合
    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

    //从集合里面移除
    fun removeActivity(activity: Activity){
        activityList.remove(activity)
    }

    //关闭所有activity
    fun finishAll(){
        for (activity in activityList){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activityList.clear()
    }
}