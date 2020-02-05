package com.github.rplezy.Dhuwitku.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Model.ItemCategory
import com.github.rplezy.Dhuwitku.R
import kotlinx.android.synthetic.main.rv_cateogory.view.*

class CategoryAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ItemCategory>?) :
    RecyclerView.Adapter<CategoryAdapter.Holder>(){

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.rv_cateogory,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.view.tv_nama.text = arrayList?.get(position)?.deskripsi
//        holder.view.tv_value_today.text = arrayList?.get(position)?.jumlah
        holder.view.tv_category.text = arrayList?.get(position)?.namaKategori
    }
}
