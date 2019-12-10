package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        ButtonSign.setOnClickListener {
            val email = usrEmail.text.toString().trim()
            val pass = usrPass.text.toString().trim()

            if (email.isEmpty()){
                usrEmail.error = "Harap isi alamat email"
                usrEmail.requestFocus()
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                usrEmail.error = "Harap masukkan email yang valid"
                usrEmail.requestFocus()
            }

            if (pass.isEmpty()){
                usrPass.error = "Harap masukkan kata sandi"
                usrPass.requestFocus()
            }

            if (pass.length < 8){
                usrPass.error = "Panjang kata sandi minimal 8 karakter"
                usrPass.requestFocus()
            }

            firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener {
                    if (!it.isSuccessful){
                        Toast.makeText(this, "Register Failed..", Toast.LENGTH_SHORT).show()
                    }else{
                        val intent = Intent(this, Login::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }
                }
        }

        Login.setOnClickListener {
            val daf = Intent(this,Login::class.java)
            daf.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(daf)
        }
    }
}
