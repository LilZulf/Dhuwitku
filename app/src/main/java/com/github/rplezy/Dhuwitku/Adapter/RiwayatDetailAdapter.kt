package com.github.rplezy.Dhuwitku.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.MainActivity
import com.github.rplezy.Dhuwitku.Model.Basic
import com.github.rplezy.Dhuwitku.Model.ItemLogTransaksi
import com.github.rplezy.Dhuwitku.R
import kotlinx.android.synthetic.main.rv_today.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatDetailAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ItemLogTransaksi>?) :
    RecyclerView.Adapter<RiwayatDetailAdapter.Holder>(){
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.rv_today,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.tv_nama.text = arrayList?.get(position)?.deskripsi
        holder.view.tv_value_today.text = arrayList?.get(position)?.jumlah
        if(arrayList?.get(position)?.state.equals("2")){
            holder.view.tv_nama.setTextColor(Color.parseColor("#29B65F"))
            holder.view.tv_value_today.setTextColor(Color.parseColor("#29B65F"))
            holder.view.tv_rp_today.setTextColor(Color.parseColor("#29B65F"))
        }
    }
}
