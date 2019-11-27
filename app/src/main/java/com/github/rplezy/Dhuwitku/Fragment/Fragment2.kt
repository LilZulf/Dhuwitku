package com.github.rplezy.Dhuwitku.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.rplezy.Dhuwitku.R
import com.github.rplezy.Dhuwitku.Riwayat
import kotlinx.android.synthetic.main.fragment_fragment2.*
import kotlinx.android.synthetic.main.fragment_fragment2.view.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_fragment2, container, false)

        view.rl_riwayat_transaksi.setOnClickListener {
            val daf = Intent(context, Riwayat::class.java)
            startActivity(daf)
        }
        return view
    }


}
