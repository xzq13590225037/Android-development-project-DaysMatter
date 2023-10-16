package com.leaf3stones.daysmaster

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xzq_user)
        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        val Userid = prefs.getString("name","")
        val userID : TextView = findViewById(R.id.userID)
        val tv_user : TextView = findViewById(R.id.tv_user)
        val exit : Button = findViewById(R.id.btn_exit)
        val user_back : Button = findViewById(R.id.user_back)
        tv_user.setText(Userid)
        userID.setText(Userid)
        exit.setOnClickListener{
            val intent = Intent(this, MainActivity_xzq::class.java)
            startActivity(intent)
        }
        user_back.setOnClickListener{
            val intent = Intent(this, zhuActivity::class.java)
            startActivity(intent)
        }

    }
}