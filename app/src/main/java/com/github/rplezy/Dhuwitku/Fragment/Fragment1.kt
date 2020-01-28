package com.github.rplezy.Dhuwitku.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Adapter.TodayAdapter
import com.github.rplezy.Dhuwitku.Add
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.DataItem
import com.github.rplezy.Dhuwitku.Model.Transaksi

import com.github.rplezy.Dhuwitku.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_add_kategori.*
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

    lateinit var rv_main_today : RecyclerView
    //var arrayList = ArrayList<Log>()


    var _fab2 : FloatingActionButton? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //setDateNow
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        val view: View = inflater.inflate(R.layout.fragment_, container, false)
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
            startActivity(daf)
        }
        view.fab3.setOnClickListener {
            val daf = Intent(context, AddKategori::class.java)
            startActivity(daf)
        }

        rv_main_today = view.findViewById(R.id.rv_main_today)

//        val llm = LinearLayoutManager(this.requireContext())
//        llm.orientation = RecyclerView.VERTICAL
//        rv_main_today.layoutManager = llm
//        adapterToday = TodayAdapter(this.requireContext(),)
//        rv_main_today.adapter = adapterToday

//        val itemTouchHelperCallback :ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
//            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//                return false
//            }
//
////            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
////                val returntoday: ArrayList<Transaksi> = getTransaksi()
////                val todaydata = adapterToday.listToday.get(viewHolder.adapterPosition)
////
////                for (today in returntoday) {
////                    returntoday.remove(today)
////                    break
////                }
////                adapterToday.notifyDataSetChanged()
////            }
//        }
            getTransaksi()
        return view
    }

//    private fun loadAll(){
//        //loading rv
//        val loading = ProgressDialog(this)
//        loading.setMessage("Memuat data...")
//        loading.show()
//        AndroidNetworking.get(EndPoint)
//            .setPriority(RenderScript.Priority.MEDIUM)
//            .build()
//            .getAsJSONObject(object : JSONObjectRequestListener{
//
//                override fun onResponse(response: JSONObject?) {
//                    arrayList.clear()
//
//                    val jsonArray = response?.optJSONArray("result")
//
//                    if(jsonArray?.length() == 0){
//                        loading.dismiss()
//                        Toast.makeText(applicationContext,"Student data is empty, Add the data first",Toast.LENGTH_SHORT).show()
//                    }
//
//                    for(i in 0 until jsonArray?.length()!!){
//
//                        val jsonObject = jsonArray?.optJSONObject(i)
//                        arrayList.add(Students(jsonObject.getString("nim"),
//                            jsonObject.getString("name"),
//                            jsonObject.getString("address"),
//                            jsonObject.getString("gender")))
//
//                        if(jsonArray?.length() - 1 == i){
//
//                            loading.dismiss()
//                            val adapter = RVAAdapterStudent(applicationContext,arrayList)
//                            adapter.notifyDataSetChanged()
//                            mRecyclerView.adapter = adapter
//
//                        }
//
//                    }
//
//                }
//
//                override fun onError(anError: ANError?) {
//                    loading.dismiss()
//                    Log.d("ONERROR",anError?.errorDetail?.toString())
//                    Toast.makeText(applicationContext,"Connection Failure",Toast.LENGTH_SHORT).show()
//                }
//            })
//
//    }

    private fun getTransaksi() {
        var gg:Int = 1
        val TransaksiModel = Service.get().getTransaksi(
            gg.toString()
        )
        TransaksiModel.enqueue(object : retrofit2.Callback<Transaksi> {
            override fun onFailure(call: Call<Transaksi>, t: Throwable) {
                Toast.makeText(activity!!,t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Transaksi>, response: Response<Transaksi>) {
                if(response.body()!!.message == "behasil ambil data"){
                   // tv_nama.text = response.body()!!.deskripsi!!
                    showData(response.body()!!.data)
                }
                else{
                    Toast.makeText(activity!!,"Error Fetching", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
    private fun showData(cars : ArrayList<DataItem>?) {
       view!!.rv_main_today.apply {
           layoutManager = LinearLayoutManager (activity)
           adapter = TodayAdapter(activity!!,cars)
       }
    }
}
