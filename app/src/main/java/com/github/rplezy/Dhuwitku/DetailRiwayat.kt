package com.github.rplezy.Dhuwitku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Adapter.TodayAdapter
import com.github.rplezy.Dhuwitku.Model.Today
import kotlinx.android.synthetic.main.activity_detail_riwayat.*

class DetailRiwayat : AppCompatActivity() {

    var valuein = ""
    var valueout = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)

        val bundle = intent
        if (bundle != null){
            valuein = bundle.getStringExtra("valuein")
            valueout = bundle.getStringExtra("valueout")
        }

        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        rv_detail_riwayat.layoutManager = llm
        //val adapter = TodayAdapter(this, todaylist)
        //rv_detail_riwayat.adapter = adapter

    }

//    private val todaylist = arrayListOf(
//        Today("makan","15.000,00","pengeluaran"),
//        Today("gaji","1.000.000,00","pemasukan"),
//        Today("minum","30.000,00","pengeluaran")
//    )

}
