package com.github.rplezy.Dhuwitku.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Model.Riwayat
import com.github.rplezy.Dhuwitku.R

class RiwayatAdapter (val context: Context,
                      val riwayatList: ArrayList<Riwayat>)
    : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_riwayat, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return riwayatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pemasukan.text = riwayatList[position].value_in
        holder.pengeluaran.text = riwayatList[position].value_out
    }

    class ViewHolder (v: View) : RecyclerView.ViewHolder(v){
        val pemasukan : TextView = v.findViewById(R.id.tv_pemasukan_riwayat)
        val pengeluaran : TextView = v.findViewById(R.id.tv_pengeluaran_riwayat)
    }
}