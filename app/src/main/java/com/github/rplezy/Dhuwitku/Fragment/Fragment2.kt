package com.github.rplezy.Dhuwitku.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.Basic
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.UserModel

import com.github.rplezy.Dhuwitku.R
import com.github.rplezy.Dhuwitku.RiwayatActivity
import kotlinx.android.synthetic.main.fragment_fragment2.view.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {
    var tanggal : TextView? = null
    var pengeluaran : TextView? = null
    var pemasukan : TextView? = null
    var selisih : Int? = null
    var tv_selisih : TextView?= null
    var pemasukanVal = 0
    var pemasukann = 0
    var pengeluaranVal = 0
    private var data : SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        data = SharedPreferences(activity!!)
        val view: View = inflater.inflate(R.layout.fragment_fragment2, container, false)
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val sdf2 = SimpleDateFormat("yyyy/MM")
        val currentDate = sdf.format(Date())
        val currentMonth = sdf2.format(Date())
        tanggal = view.findViewById(R.id.tv_tanggal)
        view.rl_riwayat_transaksi.setOnClickListener {
            val daf = Intent(context, RiwayatActivity::class.java)
            startActivity(daf)
        }
        tv_selisih = view.findViewById(R.id.tv_value_selisih)
        pengeluaran = view.findViewById(R.id.tv_value_pengeluaran)
        pemasukan = view.findViewById(R.id.tv_value_pemasukan)
        tanggal!!.text = currentMonth.toString()+"/01 - "+currentDate.toString()
        getData()
        return view
    }

    private fun getData(){
        var qrdat : String? = data!!.getString("ID_USER")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val sdf2 = SimpleDateFormat("yyyy-MM")
        val currentDate = sdf.format(Date())
        val currentMonth = sdf2.format(Date())
        var getAPI = Service.get().getLaporan(
            qrdat.toString(),
            currentDate,
            "pengeluaran"
        )

        getAPI.enqueue(object : Callback<Basic> {
            override fun onFailure(call: Call<Basic>, t: Throwable) {
                //view!!.loading.visibility = View.GONE
                Toast.makeText(activity!!,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Basic>, response: Response<Basic>) {
                if(response.body()!!.message == "behasil ambil data"){
                    pengeluaran!!.text = response.body()!!.data
                    tv_selisih!!.text = response.body()!!.data
                    pengeluaranVal = Integer.parseInt(response.body()!!.data!!)

                }
                else{
                    Toast.makeText(activity!!,"Error Fetching", Toast.LENGTH_SHORT).show()
                }

            }


        })
        var getAPI2 = Service.get().getLaporan(
            qrdat.toString(),
            currentDate,
            "pemasukan"
        )

        getAPI2.enqueue(object : Callback<Basic> {
            override fun onFailure(call: Call<Basic>, t: Throwable) {
                //view!!.loading.visibility = View.GONE
                Toast.makeText(activity!!,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Basic>, response: Response<Basic>) {
                if(response.body()!!.message == "behasil ambil data"){
                    pemasukan!!.text = response.body()!!.data
                    var pen : String? = tv_selisih!!.text.toString()
                    var total : Int? = Integer.parseInt(response.body()!!.data!!) - Integer.parseInt(pen!!)
                    tv_selisih!!.text = total.toString()
                    pemasukanVal = Integer.parseInt(response.body()!!.data!!)
                    pemasukann = pemasukanVal
                }
                else{

                    Toast.makeText(activity!!,"Error Fetching", Toast.LENGTH_SHORT).show()
                }

            }

        })

        //Toast.makeText(context, "Pengeluaran  "+ pemasukan!!, Toast.LENGTH_SHORT).show()
//        val sel = pemasukanVal - pengeluaranVal
//        tv_selisih!!.text = sel.toString()

    }


}
