package com.github.rplezy.Dhuwitku


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 200
    private val TAG = "PermissionDemo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    PERMISSION_REQUEST_CODE)
            }
        } else {
            // Permission has already been granted
        }
        ButtonLogin.setOnClickListener {
            val daf = Intent(applicationContext,MainActivity::class.java)
            finish()
            startActivity(daf)
        }
        SignUp.setOnClickListener {
            val daf = Intent(applicationContext,Register::class.java)
            finish()
            startActivity(daf)
        }
    }


}
