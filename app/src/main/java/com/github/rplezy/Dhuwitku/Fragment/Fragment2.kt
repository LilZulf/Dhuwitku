package com.github.rplezy.Dhuwitku.Fragment


import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.core.view.get
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.Basic
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.UserModel

import com.github.rplezy.Dhuwitku.R
import com.github.rplezy.Dhuwitku.RiwayatActivity
import kotlinx.android.synthetic.main.fragment_.*
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

    lateinit var laporanTanggal : RelativeLayout
    var cMin = Calendar.getInstance()
    var cMax = Calendar.getInstance()
    var stringDate = ""

    private var data : SharedPreferences? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        laporanTanggal = view.findViewById(R.id.rl_laporan_bulan)
        laporanTanggal.setOnClickListener {
//            val date = DatePickerDialog(this.requireContext(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) )
//            date.datePicker.maxDate = Calendar.getInstance().timeInMillis
//            date.show()

            val dialog = Dialog(this.requireActivity())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_tanggal)

            val min = dialog.findViewById<Button>(R.id.button_min_date)
            val max = dialog.findViewById<Button>(R.id.button_max_date)
            val ok = dialog.findViewById<Button>(R.id.button_ok)

            max.isEnabled = false

            dialog.show()

            min.setOnClickListener {
                max.isEnabled = true
                var thisAYear = cMin.get(Calendar.YEAR)
                var thisAMonth = cMin.get(Calendar.MONTH)
                var thisADay = cMin.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(this.requireActivity(), DatePickerDialog.OnDateSetListener { view2, thisYear, thisMonth, thisDay ->
                    thisAYear = thisYear
                    thisAMonth = thisMonth
                    thisADay = thisDay
                    cMin.set(thisAYear,thisAMonth,thisADay)
                }, thisAYear, thisAMonth, thisADay)
                dpd.datePicker.maxDate = Calendar.getInstance().timeInMillis
                dpd.show()
            }

            max.setOnClickListener {
                var thisAYear = cMax.get(Calendar.YEAR)
                var thisAMonth = cMax.get(Calendar.MONTH)
                var thisADay = cMax.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(this.requireActivity(), DatePickerDialog.OnDateSetListener { view2, thisYear, thisMonth, thisDay ->
                    thisAYear = thisYear
                    thisAMonth = thisMonth
                    thisADay = thisDay
                    cMax.set(thisAYear,thisAMonth,thisADay)
                }, thisAYear, thisAMonth, thisADay)
                dpd.datePicker.maxDate = Calendar.getInstance().timeInMillis
                val format = sdf.format(cMin.time)
                dpd.datePicker.minDate = convertDateToLong(format)
                dpd.show()
            }

            ok.setOnClickListener {
                Toast.makeText(this.requireContext(), "Min date is : ${sdf.format(cMin.time)}\nMax date is : ${sdf.format(cMax.time)}", Toast.LENGTH_LONG).show()
            }

        }

        getData()
        return view
    }

    private fun convertDateToLong(date : String) : Long {
        val df = SimpleDateFormat("yyyy/MM/dd")
        return df.parse(date).time
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
