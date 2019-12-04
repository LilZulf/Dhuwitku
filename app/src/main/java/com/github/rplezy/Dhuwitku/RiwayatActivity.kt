package com.github.rplezy.Dhuwitku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Adapter.RiwayatAdapter
import com.github.rplezy.Dhuwitku.Model.Riwayat
import kotlinx.android.synthetic.main.activity_riwayat.*

class RiwayatActivity : AppCompatActivity() {

    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_riwayat.layoutManager = linearLayoutManager
        val adapter = RiwayatAdapter(this, riwayat)
        rv_riwayat.adapter = adapter

    }

    private val riwayat = arrayListOf<Riwayat>(
        Riwayat("20.000,00","15.000,00"),
        Riwayat("45.000,00","24.000,00"),
        Riwayat("12.000,00","30.000,00"),
        Riwayat("60.000,00","34.500,00")
    )
}
