package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ButtonSign.setOnClickListener {
            val daf = Intent(applicationContext,Login::class.java)
            startActivity(daf)
        }
        Login.setOnClickListener {
            val daf = Intent(applicationContext,Login::class.java)
            startActivity(daf)
        }
    }
}
