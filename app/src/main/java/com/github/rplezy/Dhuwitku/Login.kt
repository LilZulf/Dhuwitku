package com.github.rplezy.Dhuwitku


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.UserModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 200
    private var data : SharedPreferences ? = null
    private val TAG = "PermissionDemo"
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        data = SharedPreferences(applicationContext!!)
        firebaseAuth = FirebaseAuth.getInstance()
        if(data!!.getSession("LOGIN") == true){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()

        }else{
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
        }


        ButtonLogin.setOnClickListener {
            val email = usrEmailLogin.text.toString().trim()
            val pass = usrPassLogin.text.toString().trim()

            if (email.isEmpty()){
                usrEmailLogin.error = "Harap isi alamat email"
                usrEmailLogin.requestFocus()
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                usrEmailLogin.error = "Harap masukkan email yang valid"
                usrEmailLogin.requestFocus()
            }

            if (pass.isEmpty()){
                usrPassLogin.error = "Harap masukkan kata sandi"
                usrPassLogin.requestFocus()
            }
            rvloading.visibility = View.VISIBLE
            doLogin()
//            firebaseAuth.signInWithEmailAndPassword(email,pass)
//                .addOnCompleteListener {
//                    if (!it.isSuccessful){
//                        if (usrPassLogin.length() < 8){
//                            usrPassLogin.error = "Password salah"
//                        }else{
//                            Toast.makeText(this, "Auth Failed", Toast.LENGTH_SHORT).show()
//                        }
//                    }else{
//                        val intent = Intent(this, MainActivity::class.java)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                        startActivity(intent)
//                    }
//                }

//            val daf = Intent(applicationContext,MainActivity::class.java)
//            finish()
//            startActivity(daf)
        }
        SignUp.setOnClickListener {
            val daf = Intent(this,Register::class.java)
            finish()
            startActivity(daf)
        }
    }
    private fun doLogin(){

        var registAPI = Service.get().doLogin(
            usrEmailLogin.text.toString(),
            usrPassLogin.text.toString()
        )

        registAPI.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if(response.body()!!.message == "Success login"){
                    rvloading.visibility = View.GONE
                    val intent = Intent(applicationContext,com.github.rplezy.Dhuwitku.MainActivity::class.java)
                    Toast.makeText(applicationContext,"Login Berhasil :)", Toast.LENGTH_SHORT).show()
                    data!!.setString("ID_USER",response.body()!!.data!!.id_user.toString())
                    data!!.session("LOGIN",true)
                    startActivity(intent)
                    finish()
                }
                else{
                    rvloading.visibility = View.GONE
                    Toast.makeText(applicationContext,response.body()!!.message, Toast.LENGTH_SHORT).show()
                }

            }

        })
    }


}
