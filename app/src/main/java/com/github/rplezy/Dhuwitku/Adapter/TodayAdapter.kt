package com.github.rplezy.Dhuwitku.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Model.Today
import com.github.rplezy.Dhuwitku.R

class TodayAdapter (val context : Context,
                    val listToday : ArrayList<Today>)
    : RecyclerView.Adapter<TodayAdapter.ViewHolder>(){

    class ViewHolder (v : View) : RecyclerView.ViewHolder(v) {
        val name : TextView = v.findViewById(R.id.tv_nama)
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
        holder.name.text = listToday[position].name
        holder.value.text = listToday[position].value
        if (listToday[position].type == "pengeluaran"){

        }else{
            holder.name.setTextColor(Color.parseColor("#2ECC71"))
            holder.name.setTypeface(holder.name.typeface, Typeface.BOLD)

            holder.value.setTextColor(Color.parseColor("#2ECC71"))
            holder.value.setTypeface(holder.value.typeface, Typeface.BOLD)
        }
    }
}