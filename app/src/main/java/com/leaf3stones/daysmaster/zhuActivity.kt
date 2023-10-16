package com.leaf3stones.daysmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.leaf3stones.daysmaster.activities.MainActivity_hjx

class zhuActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhu1)
        val Countdown : Button = findViewById(R.id._countdown)
        val Note : Button = findViewById(R.id._note)
        val History : Button = findViewById(R.id._history)
        val Login : Button = findViewById(R.id._login)
        Countdown.setOnClickListener{
            val intent = Intent(this, MainActivity_mzm::class.java)
            startActivity(intent)
        }
        Note.setOnClickListener{
            val intent = Intent(this, MainActivity_hjx::class.java)
            startActivity(intent)
        }
        History.setOnClickListener{
            val intent = Intent(this, MainActivity_lhp::class.java)
            startActivity(intent)
        }
        Login.setOnClickListener{
            val intent = Intent(this, MainActivity_xzq::class.java)
            startActivity(intent)
        }
    }
}
