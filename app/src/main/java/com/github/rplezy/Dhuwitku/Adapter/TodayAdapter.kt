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

class TodayAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ItemLogTransaksi>?) :
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
        if(arrayList?.get(position)?.state.equals("2")){
            holder.view.tv_nama.setTextColor(Color.parseColor("#29B65F"))
            holder.view.tv_value_today.setTextColor(Color.parseColor("#29B65F"))
            holder.view.tv_rp_today.setTextColor(Color.parseColor("#29B65F"))
        }
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
        holder.view.iv_delete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Apakah Anda Yakin ?")
            builder.setMessage("Data Yang Akan Dihapus "+arrayList?.get(position)?.deskripsi)
            builder.setPositiveButton("Iya"){dialog, which ->
                // Do something when user press the positive button
                //Toast.makeText(context,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()
                var getApi = Service.get().deleteLog(
                    arrayList?.get(position)?.id_log.toString()
                )
                getApi.enqueue(object : Callback<Basic> {
                    override fun onFailure(call: Call<Basic>, t: Throwable) {
                        //view!!.loading.visibility = View.GONE
                        Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Basic>, response: Response<Basic>) {
                        if(response.body()!!.message == "behasil delete data"){

                            val i = Intent(context,MainActivity::class.java)
                            startActivity(context,i,null)

                        }
                        else{
                            Toast.makeText(context,"Error Fetching", Toast.LENGTH_SHORT).show()
                        }

                    }


                })
            }


            // Display a negative button on alert dialog
            builder.setNegativeButton("Tidak"){dialog,which ->
                Toast.makeText(context,"Kamu tidak setuju.",Toast.LENGTH_SHORT).show()
            }
            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
//            Toast.makeText(context,"Data Yang Akan Dihapus "+arrayList?.get(position)?.id_log,Toast.LENGTH_SHORT).show()
        }
    }
}
