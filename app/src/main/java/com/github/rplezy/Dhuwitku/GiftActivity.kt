package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.AddTransaksi
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_gift.*

class GiftActivity : AppCompatActivity() {
    private var data : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift)
        data = SharedPreferences(this)
        getData()
        ButtonSubmit.setOnClickListener {
            val nominal = usrNominal.text.toString().trim()
            val judul = usrJudul.text.toString().trim()
            val pesan = usrPesan.text.toString().trim()
            if (nominal.isEmpty()){
                usrNominal.error = "Harap isi Nominal"
                usrNominal.requestFocus()
            }
            else if(judul.isEmpty()){
                usrJudul.error = "Harap isi judul"
                usrJudul.requestFocus()

            }else if(pesan.isEmpty()){
                usrPesan.error = "Harap isi Pesan"
                usrPesan.requestFocus()
            }
            else{
                gift()
            }

        }
    }
    private fun getData(){
        var strTujuan: String = intent.getStringExtra("Tujuan")
        var qrdat : String? = data!!.getString("ID_USER")
        var getAPI = Service.get().getById(
            strTujuan
        )

        getAPI.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(this@GiftActivity,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if(response.body()!!.message == "Succes Fetching data"){
                   tv_penerima.text = "Penerima : "+response.body()!!.data!!.username
                   tv_email.text = "Email : "+response.body()!!.data!!.email
                }
                else{
                    Toast.makeText(this@GiftActivity,"Error Fetching", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }
    private fun gift(){
        var strTujuan: String = intent.getStringExtra("Tujuan")
        var id_user : String? = data!!.getString("ID_USER")
        val nominal = usrNominal.text.toString().trim()
        val title = usrJudul.text.toString().trim()
        val pesan = usrJudul.text.toString().trim()
        var getAPI = Service.get().doGift(
            id_user.toString(),
            nominal,
            strTujuan,
            title,
            pesan
        )

        getAPI.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(this@GiftActivity,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if(response.body()!!.message == "Done Eak"){
                    Toast.makeText(this@GiftActivity,"Berhasil Gift Saldo", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@GiftActivity,MainActivity::class.java);
                    startActivity(intent)
                    finish()
//                    tv_penerima.text = "Penerima : "+response.body()!!.data!!.username
//                    tv_email.text = "Email : "+response.body()!!.data!!.email
                }
                else{
                    Toast.makeText(this@GiftActivity,"Error Fetching", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }


}
