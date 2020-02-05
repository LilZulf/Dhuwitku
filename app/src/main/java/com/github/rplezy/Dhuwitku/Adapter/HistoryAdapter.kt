package com.github.rplezy.Dhuwitku.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Model.ItemHistory
import com.github.rplezy.Dhuwitku.R
import kotlinx.android.synthetic.main.rv_history.view.*

class HistoryAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ItemHistory>?
) :
    RecyclerView.Adapter<HistoryAdapter.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_history,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.tv_nominal.text = arrayList?.get(position)?.nominal
        holder.view.tv_time.text = arrayList?.get(position)?.date
    }
}