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

import com.github.rplezy.Dhuwitku.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_.*
import kotlinx.android.synthetic.main.fragment_.view.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    var _fab2 : FloatingActionButton? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_, container, false)
        view.fab1.setOnClickListener {
            view.fab2.visibility = View.VISIBLE
            view.fab3.visibility = View.VISIBLE
        }
        view.rv_main.setOnClickListener {
            view.fab2.visibility = View.GONE
            view.fab3.visibility = View.GONE
        }

        return view
    }



}
