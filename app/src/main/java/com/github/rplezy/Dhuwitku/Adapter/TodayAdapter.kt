package com.github.rplezy.Dhuwitku.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Model.DataItem
import com.github.rplezy.Dhuwitku.Model.Today
import com.github.rplezy.Dhuwitku.Model.Transaksi
import com.github.rplezy.Dhuwitku.R

class TodayAdapter (val context : Context,
                    val listToday : ArrayList<DataItem>)
    : RecyclerView.Adapter<TodayAdapter.ViewHolder>(){

    class ViewHolder (v : View) : RecyclerView.ViewHolder(v) {
        val desc : TextView = v.findViewById(R.id.tv_nama)
        val value : TextView = v.findViewById(R.id.tv_value_today)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_today, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listToday.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.desc.text = listToday[position].deskripsi
        holder.value.text = listToday[position].jumlah.toString()
        if (listToday[position].state == "2"){

        }else{
            holder.desc.setTextColor(Color.parseColor("#2ECC71"))
            holder.desc.setTypeface(holder.desc.typeface, Typeface.BOLD)

            holder.value.setTextColor(Color.parseColor("#2ECC71"))
            holder.value.setTypeface(holder.value.typeface, Typeface.BOLD)
        }
    }
}