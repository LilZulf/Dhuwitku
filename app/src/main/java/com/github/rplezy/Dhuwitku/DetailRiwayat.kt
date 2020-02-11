package com.github.rplezy.Dhuwitku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Adapter.RiwayatDetailAdapter
import com.github.rplezy.Dhuwitku.Adapter.TodayAdapter
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.ItemLogTransaksi
import com.github.rplezy.Dhuwitku.Model.Today
import com.github.rplezy.Dhuwitku.Model.Transaksi
import kotlinx.android.synthetic.main.activity_detail_riwayat.*
import kotlinx.android.synthetic.main.header2.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailRiwayat : AppCompatActivity() {

    var pengeluaran = ""
    var pemasukan = ""
    var idTransaksi = ""
    var tanggal = ""
    var tv_date : TextView? = null
    var tv_keluar : TextView? = null
    var tv_masuk : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)

        val bundle = intent
        if (bundle != null){
            pengeluaran = bundle.getStringExtra("penge")
            pemasukan = bundle.getStringExtra("pema")
            idTransaksi = bundle.getStringExtra("idTrans")
            tanggal = bundle.getStringExtra("tanggal")
        }
        tv_date = findViewById(R.id.tv_date)
        tv_keluar = findViewById(R.id.UangKeluar)
        tv_masuk =findViewById(R.id.UangMasuk)
        getLog(idTransaksi!!)
        iv_back.setOnClickListener {
            finish()
        }

    }
    private fun getLog(idTransaksi: String?) {

        val TransaksiModel = Service.get().getTransaksi(
            idTransaksi.toString()
        )
        TransaksiModel.enqueue(object : retrofit2.Callback<Transaksi> {
            override fun onFailure(call: Call<Transaksi>, t: Throwable) {
                Toast.makeText(this@DetailRiwayat, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Transaksi>, response: Response<Transaksi>) {
                if (response.body()!!.message == "behasil ambil data") {
                    tv_date!!.text = tanggal
                    tv_keluar!!.text = pengeluaran
                    tv_masuk!!.text = pemasukan
                    showData(response.body()?.data)

                } else {
                    Toast.makeText(this@DetailRiwayat, "Error Fetching", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

//    private val todaylist = arrayListOf(
//        Today("makan","15.000,00","pengeluaran"),
//        Today("gaji","1.000.000,00","pemasukan"),
//        Today("minum","30.000,00","pengeluaran")
//    )
    private fun showData(cars: ArrayList<ItemLogTransaksi>?) {
        rv_detail_riwayat.apply {
            layoutManager = LinearLayoutManager(this@DetailRiwayat)
            adapter = RiwayatDetailAdapter(this@DetailRiwayat, cars)
        }
    }

}
