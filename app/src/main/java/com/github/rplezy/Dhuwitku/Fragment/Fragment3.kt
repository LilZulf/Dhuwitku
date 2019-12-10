package com.github.rplezy.Dhuwitku.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rplezy.Dhuwitku.QrGenerate
import com.github.rplezy.Dhuwitku.R
import kotlinx.android.synthetic.main.fragment3.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment3, container, false)

        btn_kirim.setOnClickListener{
            val intent = Intent(context, QrGenerate::class.java)
            startActivity(intent)
        }
    }




}
