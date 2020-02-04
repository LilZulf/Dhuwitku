package com.github.rplezy.Dhuwitku.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Model.DataItem
import com.github.rplezy.Dhuwitku.Model.Transaksi
import com.github.rplezy.Dhuwitku.R
import kotlinx.android.synthetic.main.rv_today.view.*

class TodayAdapter(
    private val context: Context,
    private val arrayList: ArrayList<DataItem>?) :
        RecyclerView.Adapter<TodayAdapter.Holder>(){
    private var positiion = -1
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.rv_today,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.tv_nama.text = arrayList?.get(position)?.deskripsi
        holder.view.tv_value_today.text = arrayList?.get(position)?.jumlah

        if(positiion == position){
            holder.view.rl_hapus.visibility = View.VISIBLE
        } else {
            holder.view.rl_hapus.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            positiion = position
            notifyDataSetChanged()
        }
        holder.view.rl_hapus.setOnClickListener {
            holder.view.rl_hapus.visibility = View.GONE
            positiion = -1
            notifyDataSetChanged()
        }
    }
}
