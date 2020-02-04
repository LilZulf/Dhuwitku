package com.github.rplezy.Dhuwitku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rplezy.Dhuwitku.Adapter.CategoryAdapter
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.Category
import com.github.rplezy.Dhuwitku.Model.ItemCategory
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import kotlinx.android.synthetic.main.activity_add_kategori.*
import kotlinx.android.synthetic.main.header2.*
import retrofit2.Call
import retrofit2.Response

class AddKategori : AppCompatActivity() {
    private var data : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kategori)
        data = SharedPreferences(this)
        getKategori()
        BtnKategori.setOnClickListener {
            val ketegori = AddKategori.text.toString().trim()
            if (ketegori.isEmpty()){
                AddKategori.error = "Harap isi"
                AddKategori.requestFocus()
            }else{
                addKategori()
            }
        }
        iv_back.setOnClickListener {
            finish()
        }
    }
    private fun addKategori() {

        var qrdat : String? = data!!.getString("ID_USER")
        val ketegori = AddKategori.text.toString().trim()
        val CategoryModel = Service.get().addCategory(
            qrdat.toString(),
            ketegori
        )
        CategoryModel.enqueue(object : retrofit2.Callback<Category> {
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@AddKategori,t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.body()!!.message == "behasil tambah data"){
                    // tv_nama.text = response.body()!!.deskripsi!!
                    Toast.makeText(this@AddKategori,"Berhasil add", Toast.LENGTH_SHORT).show()
                    AddKategori.text = null
                   getKategori()
                }
                else{
                    Toast.makeText(this@AddKategori,"Error Fetching", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@AddKategori,t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.body()!!.message == "behasil ambil data"){
                    // tv_nama.text = response.body()!!.deskripsi!!
                    showData(response.body()!!.data)
                }
                else{
                    Toast.makeText(this@AddKategori,"Error Fetching", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
    private fun showData(cars : ArrayList<ItemCategory>?) {
            rvCategory.apply {
            layoutManager = LinearLayoutManager (this@AddKategori)
            adapter = CategoryAdapter(this@AddKategori,cars)
        }
    }
}
