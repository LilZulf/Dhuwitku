package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ButtonLogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
