package com.github.rplezy.Dhuwitku.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.ConfirmTopup
import com.github.rplezy.Dhuwitku.DetailRiwayat
import com.github.rplezy.Dhuwitku.Model.ItemLogTransaksi
import com.github.rplezy.Dhuwitku.Model.ItemRiwayat
import com.github.rplezy.Dhuwitku.R
import kotlinx.android.synthetic.main.rv_riwayat.view.*

class RiwayatAdapter(private val context: Context,
                     private val arrayList: ArrayList<ItemRiwayat>?):
    RecyclerView.Adapter<RiwayatAdapter.Holder>(){
    class Holder(val view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.rv_riwayat,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.tv_pemasukan_riwayat.text = arrayList?.get(position)?.pemasukan
        holder.view.tv_pengeluaran_riwayat.text = arrayList?.get(position)?.pengeluaran
        holder.view.rv_parent.setOnClickListener {
            (context as Activity).startActivity(
                Intent(context, DetailRiwayat::class.java)
                    .putExtra("tanggal" , arrayList?.get(position)?.tanggal)
                    .putExtra("idTrans", arrayList?.get(position)?.id_transaksi)
                    .putExtra("pema", arrayList?.get(position)?.pemasukan)
                    .putExtra("penge",arrayList?.get(position)?.pengeluaran)
            )
        }
    }
}