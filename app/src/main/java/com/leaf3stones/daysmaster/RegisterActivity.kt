package com.leaf3stones.daysmaster

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xzq_register)
        val acc_edit : EditText = findViewById(R.id.accountEdit)
        val pwd_edit : EditText = findViewById(R.id.passwordEdit)
        val aff_edit : EditText = findViewById(R.id.affpwdEdit)
        padPic(acc_edit, R.drawable.acc)
        padPic(pwd_edit, R.drawable.pwd)
        padPic(aff_edit, R.drawable.pwd)
        val registerBtn : Button = findViewById(R.id.registerbtn)
        registerBtn.setOnClickListener{
            val inputUsername = acc_edit.text.toString()
            val inputUserpwd = pwd_edit.text.toString()
            val inputAff = aff_edit.text.toString()
            if(inputUserpwd != "" && inputUsername != "" && inputAff != ""){
                if (inputUserpwd == inputAff){
                    Toast.makeText(this, "注册成功！",Toast.LENGTH_SHORT).show()
                    val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
                    editor.putString("name",inputUsername)
                    editor.putString("password",inputUserpwd)
                    editor.apply()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "两次密码不一致！",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "请输入注册信息！",Toast.LENGTH_SHORT).show()
            }
        }
    }

    data class user(val name:String, val pwd:String)

    private fun padPic(edit : EditText, pic : Int){
        val draw1 : Drawable = getResources().getDrawable(pic)
        draw1.setBounds(0, 0, 100, 100)
        edit.setCompoundDrawables(draw1, null, null, null)
    }
}
