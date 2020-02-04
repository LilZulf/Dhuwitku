package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.AddTransaksi
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import kotlinx.android.synthetic.main.activity_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Add : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var kategori = arrayOf(1,2)
    var tipe = arrayOf(1,2,3,4,5)

    var spinnerkat: Spinner? = null
    var spinnertipe: Spinner? = null

//    val katpick:String = kategori_spinner.selectedItem.toString()
//    val tipepick:String = tipe_spinner.selectedItem.toString()
//    var selected = ""
    private var data : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        data = SharedPreferences(this)
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

        ButtonAdd.setOnClickListener {
            val judul = AddJudul.text.toString().trim()
            val jml = AddJumlah.text.toString().trim()
            if(judul.isEmpty()){
                AddJudul.error = "Harap Isi"
                AddJudul.requestFocus()
            }
            else if(jml.isEmpty()){
                AddJumlah.error = "Harap Isi"
                AddJumlah.requestFocus()
            }
            else{
                create()
            }

        }
    }

    private fun create(){

        val selectkat = kategori_spinner.selectedItem.toString()
        val selecttipe = tipe_spinner.selectedItem.toString()
//        Toast.makeText(this@Add, selected, Toast.LENGTH_SHORT).show()
        val transid:String? = data!!.getString("ID_USER")
        var addTransaksi = Service.get().tambahTransaksi(
            transid.toString(),
            selectkat,
            AddJudul.text.toString(),
            AddJumlah.text.toString(),
            selecttipe
        )

        addTransaksi.enqueue(object : Callback<AddTransaksi> {
            override fun onFailure(call: Call<AddTransaksi>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AddTransaksi>, response: Response<AddTransaksi>) {
                if(response.body()!!.message == "behasil tambah data"){
                    val intent = Intent(this@Add, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext,response.body()!!.message, Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this,"Item", Toast.LENGTH_SHORT).show()
    }


}
