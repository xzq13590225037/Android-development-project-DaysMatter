package com.leaf3stones.daysmaster

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xzq_login)
        val loginBtn : Button = findViewById(R.id.loginbtn)
        val acc_edit : EditText = findViewById(R.id.accountEdit)
        val pwd_edit : EditText = findViewById(R.id.passwordEdit)
        padPic(acc_edit, R.drawable.acc)
        padPic(pwd_edit, R.drawable.pwd)
        var n = 0
        loginBtn.setOnClickListener{
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val Extra_user = prefs.getString("name","")
            val Extra_pwd = prefs.getString("password", "")
            val inputacc = acc_edit.text.toString()
            val inputpwd = pwd_edit.text.toString()
            if(Extra_user == inputacc && Extra_pwd == inputpwd){
                Toast.makeText(this, "登录成功！",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            }else{
                n += 1
                Toast.makeText(this, "登录失败！",Toast.LENGTH_SHORT).show()
                if (n>=3){
                    val intent = Intent("com.leaf3stones.daysmaster.TextReceiver")
                    sendBroadcast(intent)
                }
            }
        }

    }
    private fun padPic(edit : EditText, pic : Int){
        val draw1 : Drawable = getResources().getDrawable(pic)
        draw1.setBounds(0, 0, 100, 100)
        edit.setCompoundDrawables(draw1, null, null, null)
    }
}