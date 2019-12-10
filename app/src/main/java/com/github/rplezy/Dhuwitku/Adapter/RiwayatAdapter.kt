package com.github.rplezy.Dhuwitku.Adapter

import android.content.Context
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Model.Riwayat
import com.github.rplezy.Dhuwitku.R
import java.util.*
import kotlin.collections.ArrayList

class RiwayatAdapter (val context: Context,
                      val riwayatList: ArrayList<Riwayat>,
                      val onItemClickListener: OnItemClick)
    : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_riwayat, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return riwayatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val value1 = riwayatList[position].value_in
        val value2 = riwayatList[position].value_out

        holder.pemasukan.text = value1
        holder.pengeluaran.text = value2

        holder.itemView.setOnClickListener {
            onItemClickListener.OnItemClickListener(value1, value2)
        }

    }

    class ViewHolder (v: View) : RecyclerView.ViewHolder(v){
        val pemasukan : TextView = v.findViewById(R.id.tv_pemasukan_riwayat)
        val pengeluaran : TextView = v.findViewById(R.id.tv_pengeluaran_riwayat)
    }

    interface OnItemClick : View.OnClickListener{
        fun OnItemClickListener(value_in : String, value_out: String)
    }

}