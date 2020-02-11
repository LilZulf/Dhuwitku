package com.github.rplezy.Dhuwitku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rplezy.Dhuwitku.Adapter.RiwayatAdapter
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.ItemRiwayat
import com.github.rplezy.Dhuwitku.Model.Riwayat
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import kotlinx.android.synthetic.main.activity_riwayat.*
import kotlinx.android.synthetic.main.header2.*
import retrofit2.Call
import retrofit2.Response

class RiwayatActivity : AppCompatActivity(){

    private var data: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)
        data = SharedPreferences(this)
        getTransaksi()
        iv_back.setOnClickListener {
            finish()
        }
    }
    private fun getTransaksi() {

        var qrdat: String? = data!!.getString("ID_USER")

        val TransaksiModel = Service.get().riwayat(
            qrdat.toString()
        )
        TransaksiModel.enqueue(object : retrofit2.Callback<Riwayat> {
            override fun onFailure(call: Call<Riwayat>, t: Throwable) {
                Toast.makeText(this@RiwayatActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Riwayat>, response: Response<Riwayat>) {
                if (response.body()!!.message == "behasil ambil data") {
                        showData(response.body()!!.data)
                } else {
                    Toast.makeText(this@RiwayatActivity, "Belum Melakukan transaksi", Toast.LENGTH_SHORT).show()

                }
            }

        })
    }
    private fun showData(cars: ArrayList<ItemRiwayat>?) {
        rv_riwayat.apply {
            layoutManager = LinearLayoutManager(this@RiwayatActivity)
            adapter = RiwayatAdapter(this@RiwayatActivity, cars)
        }
    }
}
