package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.UserModel
import kotlinx.android.synthetic.main.activity_confirm_topup.*
import kotlinx.android.synthetic.main.header2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmTopup : AppCompatActivity() {
    private var data : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_topup)
        data = SharedPreferences(this)
        var strNominal: String = intent.getStringExtra("Nominal")
        tv_nominal.text = "Rp."+strNominal
        btn_done.setOnClickListener {
            getData()
        }
        iv_back.setOnClickListener {
            finish()
        }
    }
    private fun getData(){
        var strNominal: String = intent.getStringExtra("Nominal")
        var qrdat : String? = data!!.getString("ID_USER")
        var topup = Service.get().doTopUp(
            qrdat.toString(),
            strNominal
        )

        topup.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(this@ConfirmTopup,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if(response.body()!!.message == "Terimakasih Telah Melakukan TopUp Tunggu Konfirmasi admin"){
                    val intent = Intent(this@ConfirmTopup,MainActivity::class.java);
                    Toast.makeText(this@ConfirmTopup,"Terimakasih Telah Melakukan TopUp Tunggu Konfirmasi admin", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@ConfirmTopup,"Error Fetching", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }
}
