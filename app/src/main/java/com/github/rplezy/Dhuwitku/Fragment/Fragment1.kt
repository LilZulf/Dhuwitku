package com.github.rplezy.Dhuwitku.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Adapter.TodayAdapter
import com.github.rplezy.Dhuwitku.Add
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.ItemLogTransaksi
import com.github.rplezy.Dhuwitku.Model.MainTransaksi
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.Transaksi

import com.github.rplezy.Dhuwitku.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_.view.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {
    private var data: SharedPreferences? = null
    lateinit var rv_main_today: RecyclerView
    private var loader: LinearLayout? = null
    private var ukl : TextView? = null
    private var um : TextView? = null
    private var idTrans : String? = null
    //var arrayList = ArrayList<Log>()


    var _fab2: FloatingActionButton? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //setDateNow
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())

        val view: View = inflater.inflate(R.layout.fragment_, container, false)
        loader = view.findViewById(R.id.loading)
        ukl = view.findViewById(R.id.UangKeluar)
        um = view.findViewById(R.id.UangMasuk)
        data = SharedPreferences(activity!!)
        view.fab1.setOnClickListener {
            view.fab2.show()
            view.fab3.show()
        }
        view.rv_main.setOnClickListener {
            view.fab2.hide()
            view.fab3.hide()
        }
        view.date.setText(currentDate)
        view.fab2.setOnClickListener {
            val daf = Intent(context, Add::class.java)
            daf.putExtra("idTransaksi", idTrans )
            startActivity(daf)
        }

        rv_main_today = view.findViewById(R.id.rv_main_today)
        loader!!.visibility = View.VISIBLE
        getTransaksi()
        return view
    }

    private fun getLog(idTransaksi: Int?) {
        var qrdat: String? = data!!.getString("ID_USER")
        var gg: Int = 1
        val TransaksiModel = Service.get().getTransaksi(
            idTransaksi.toString()
        )
        TransaksiModel.enqueue(object : retrofit2.Callback<Transaksi> {
            override fun onFailure(call: Call<Transaksi>, t: Throwable) {
                loader!!.visibility = View.GONE
                Toast.makeText(activity!!, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Transaksi>, response: Response<Transaksi>) {
                if (response.body()!!.message == "behasil ambil data") {
                    // tv_nama.text = response.body()!!.deskripsi!!
                    loader!!.visibility = View.GONE
                    showData(response.body()?.data)

                } else {
                    loader!!.visibility = View.GONE
                    Toast.makeText(activity!!, "Error Fetching", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun getTransaksi() {

        var qrdat: String? = data!!.getString("ID_USER")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())

        val TransaksiModel = Service.get().getMainTransaksi(
            qrdat.toString(),
            currentDate.toString()
        )
        TransaksiModel.enqueue(object : retrofit2.Callback<MainTransaksi> {
            override fun onFailure(call: Call<MainTransaksi>, t: Throwable) {
                Toast.makeText(activity!!, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MainTransaksi>, response: Response<MainTransaksi>) {
                if (response.body()!!.message == "behasil ambil data") {
                    // tv_nama.text = response.body()!!.deskripsi!!
                    ukl!!.text = response.body()!!.data.pengeluaran
                    um!!.text = response.body()!!.data.pemasukan
                    idTrans = response.body()!!.data.id_transaksi.toString()
                    getLog(Integer.parseInt(response.body()!!.data.id_transaksi.toString()))
                    // showData(response.body())
                } else {
                    Toast.makeText(activity!!, "Data Kadaluarsa Tunggu Sebentar", Toast.LENGTH_SHORT).show()
                    addTransaksi()
                }
            }

        })
    }
    private fun addTransaksi(){
        var qrdat: String? = data!!.getString("ID_USER")
        val TransaksiModel = Service.get().addMainTransaksi(
            qrdat.toString()
        )
        TransaksiModel.enqueue(object : retrofit2.Callback<Transaksi> {
            override fun onFailure(call: Call<Transaksi>, t: Throwable) {
                Toast.makeText(activity!!, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Transaksi>, response: Response<Transaksi>) {
                if (response.body()!!.message == "behasil tambah data") {
                    // tv_nama.text = response.body()!!.deskripsi!!
                    getTransaksi()
                    // showData(response.body())
                } else {
                    Toast.makeText(activity!!, "Error", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }
    private fun showData(cars: ArrayList<ItemLogTransaksi>?) {
        rv_main_today.apply {
            layoutManager = LinearLayoutManager(activity!!)
            adapter = TodayAdapter(activity!!, cars)
        }
    }
}
