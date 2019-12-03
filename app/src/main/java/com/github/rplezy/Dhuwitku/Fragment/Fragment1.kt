package com.github.rplezy.Dhuwitku.Fragment


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.fragment.app.Fragment
import com.github.rplezy.Dhuwitku.Add
import com.github.rplezy.Dhuwitku.AddKategori
import com.github.rplezy.Dhuwitku.MainActivity
import com.github.rplezy.Dhuwitku.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_.*
import kotlinx.android.synthetic.main.fragment_.view.*


/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    var fabround : FloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_, container, false)
        //val fabround = inflater.inflate(R.id.fab, container, false
        //val binding = DataBindingUtil.inflate<Fragment1Binding>(this, R.layout.fragment_, container, false)
        view.fab.setOnClickListener {
            val intent = Intent(context, AddKategori::class.java)

            startActivity(intent)
        }
        return view

    }
    fun rotateFab(v: View, rotate: Boolean): Boolean {
        v.animate().setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
            })
            .rotation(if (rotate) 135f else 0f)
        return rotate
    }


}
