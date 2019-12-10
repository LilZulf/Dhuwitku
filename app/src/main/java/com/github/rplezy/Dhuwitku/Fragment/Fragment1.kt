package com.github.rplezy.Dhuwitku.Fragment


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rplezy.Dhuwitku.Adapter.TodayAdapter
import com.github.rplezy.Dhuwitku.Model.Today

import com.github.rplezy.Dhuwitku.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_.*
import kotlinx.android.synthetic.main.fragment_.view.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    lateinit var rv_main_today : RecyclerView
    lateinit var adapterToday : TodayAdapter

    var _fab2 : FloatingActionButton? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_, container, false)
        view.fab1.setOnClickListener {
            view.fab2.visibility = View.VISIBLE
            view.fab3.visibility = View.VISIBLE
        }
        view.rv_main.setOnClickListener {
            view.fab2.visibility = View.GONE
            view.fab3.visibility = View.GONE
        }

        rv_main_today = view.findViewById(R.id.rv_main_today)

        val llm = LinearLayoutManager(this.requireContext())
        llm.orientation = RecyclerView.VERTICAL
        rv_main_today.layoutManager = llm
        adapterToday = TodayAdapter(this.requireContext(),todaylist)
        rv_main_today.adapter = adapterToday

        val itemTouchHelperCallback :ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val returntoday: ArrayList<Today> = todaylist
                val todaydata = adapterToday.listToday.get(viewHolder.adapterPosition)

                for (today in returntoday) {
                    returntoday.remove(today)
                    break
                }
                adapterToday.notifyDataSetChanged()
            }
        }

        return view
    }

    private val todaylist = arrayListOf(
        Today("makan","30.000,00","pengeluaran"),
        Today("minum","25.000,00","pengeluaran"),
        Today("bonus","300.000,00","pemasukan"),
        Today("iuran","5.000,00","pengeluaran")
    )

}
