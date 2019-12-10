package com.github.rplezy.Dhuwitku

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
                        showDialog()
                    }
                }
        }

        Login.setOnClickListener {
            val daf = Intent(this,Login::class.java)
            daf.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(daf)
        }
    }

    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_verification)
        val verify = dialog.findViewById(R.id.btn_email_verif) as Button
        verify.setOnClickListener {
            val user = firebaseAuth.currentUser
            user?.sendEmailVerification()
                ?.addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Your email verification sent to ${user.email}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Login::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Failed to send email verification.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}
