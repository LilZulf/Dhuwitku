package com.github.rplezy.Dhuwitku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.AddTransaksi
import com.github.rplezy.Dhuwitku.Model.Category
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import kotlinx.android.synthetic.main.activity_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Add : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var tipe = arrayOf(1,2)
    var kategori = arrayOf(1,2,3,4,5)

    var spinnerkat: Spinner? = null
    var spinnertipe: Spinner? = null
    var selectKat = ""

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
//        val kat = ArrayAdapter(this, android.R.layout.simple_spinner_item, kategori)
        val tip = ArrayAdapter(this , android.R.layout.simple_spinner_item, tipe)
//        // Set layout to use when the list of choices appear
//        kat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        // Set Adapter to Spinner
//        spinnerkat!!.setAdapter(kat)
        spinnertipe!!.setAdapter(tip)

//        spinnerkat!!.setOnItemSelectedListener(object : OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                val selectedName = parent.getItemAtPosition(position).toString()
//                //                requestDetailDosen(selectedName);
//                Toast.makeText(applicationContext, "Kamu memilih kategori $selectedName", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        })

        getKategori()

        ButtonAdd.setOnClickListener {
            val judul = AddJudul.text.toString().trim()
            val jumlah = AddJumlah.text.toString().trim()

            if (judul.isEmpty()){
                AddJudul.error = "Harap masukkan judul transaksi"
                AddJudul.requestFocus()
            }

            if (jumlah.isEmpty()){
                AddJumlah.error = "Harap masukkan Jumlah nominal transaksi"
                AddJumlah.requestFocus()
            }
            else {
                loading_progress.visibility = View.VISIBLE
                create()
            }
        }

    }

    private fun create() {

        //val selectkat = kategori_spinner.selectedItem.toString()
        val selecttipe = tipe_spinner.selectedItem.toString()
//        Toast.makeText(this@Add, selected, Toast.LENGTH_SHORT).show()
        val transid:String? = data!!.getString("ID_USER")
        var addTransaksi = Service.get().tambahTransaksi(
            transid.toString(),
            selectKat,
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
                    loading_progress.visibility = View.GONE
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(applicationContext,response.body()!!.message, Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun getKategori() {
        var gg:Int = 1
        var qrdat : String? = data!!.getString("ID_USER")
        val CategoryModel = Service.get().getCategory(
            qrdat.toString()
        )
        CategoryModel.enqueue(object : retrofit2.Callback<Category> {
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@Add,t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.body()!!.message == "behasil ambil data"){
//                     tv_nama.text = response.body()!!.deskripsi!!

                    val category : ArrayList<String> = ArrayList()

                    for (i in 0 until response.body()!!.data!!.size){
                        category.add(i, response.body()!!.data!![i].namaKategori!!)
                    }

                    spinnerkat!!.setOnItemSelectedListener(object : OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                        ) {
                            val selectedName = parent.getItemAtPosition(position).toString()
                            for (i in 0 until response.body()!!.data!!.size){
                                if (response.body()!!.data!![i].namaKategori == selectedName){
                                    selectKat = response.body()!!.data!![i].id_category!!
                                    Toast.makeText(applicationContext, "Kamu memilih kategori $selectedName dengan id: ${response.body()!!.data!![i].id_category}", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                            //                requestDetailDosen(selectedName);

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    })

                    val kat = ArrayAdapter(this@Add, android.R.layout.simple_spinner_item, category)
                    // Set layout to use when the list of choices appear
                    kat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Set Adapter to Spinner
                    spinnerkat!!.setAdapter(kat)

                }
                else{
                    Toast.makeText(this@Add,"Error Fetching", Toast.LENGTH_SHORT).show()
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
