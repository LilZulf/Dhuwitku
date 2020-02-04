package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Adapter.RiwayatAdapter
import com.github.rplezy.Dhuwitku.Model.Riwayat
import kotlinx.android.synthetic.main.activity_riwayat.*
import kotlinx.android.synthetic.main.header2.*

class RiwayatActivity : AppCompatActivity(), RiwayatAdapter.OnItemClick{

    lateinit var linearLayoutManager: LinearLayoutManager
    private var PRIVATE_MODE = 0
    private var PREF_NAME = "PREFS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_riwayat.layoutManager = linearLayoutManager
        val adapter = RiwayatAdapter(this, riwayat, this)
        rv_riwayat.adapter = adapter
        iv_back.setOnClickListener {
            finish()
        }

    }

    private val riwayat = arrayListOf(
        Riwayat("20.000,00","15.000,00"),
        Riwayat("45.000,00","24.000,00"),
        Riwayat("12.000,00","30.000,00"),
        Riwayat("60.000,00","34.500,00")
    )

    override fun OnItemClickListener(value_in: String, value_out: String) {
        val intent = Intent(this, DetailRiwayat::class.java)
        intent.putExtra("valuein",value_in)
        intent.putExtra("valueout",value_out)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {

    }
}
