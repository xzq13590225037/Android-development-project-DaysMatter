package com.leaf3stones.daysmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_xzq : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xzq_main)
        val loginbtn : Button = findViewById(R.id.login)
        loginbtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val registerbtn : Button = findViewById(R.id.register)
        registerbtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        val login_back :Button = findViewById(R.id.login_back)
        login_back.setOnClickListener{
            val intent = Intent(this, zhuActivity::class.java)
            startActivity(intent)
        }
    }
}