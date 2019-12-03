package com.github.rplezy.Dhuwitku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class Add : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var kategori = arrayOf("Pemasukan","Pengeluaran")
    var tipe = arrayOf("Transportasi","Makan","Tagihan","Kebutuhan duniawi")

    var spinnerkat: Spinner? = null
    var spinnertipe: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        spinnerkat = this.kategori_spinner
        spinnerkat!!.setOnItemSelectedListener(this)

        spinnertipe = this.tipe_spinner
        spinnertipe!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val kat = ArrayAdapter(this, android.R.layout.simple_spinner_item, kategori)
        val tip = ArrayAdapter(this , android.R.layout.simple_spinner_item, tipe)
        // Set layout to use when the list of choices appear
        kat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinnerkat!!.setAdapter(kat)
        spinnertipe!!.setAdapter(tip)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this,"Item", Toast.LENGTH_SHORT).show()
    }


}
