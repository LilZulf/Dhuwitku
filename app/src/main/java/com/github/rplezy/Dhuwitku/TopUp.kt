package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_top_up.*
import kotlinx.android.synthetic.main.header2.*

class TopUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        iv_back.setOnClickListener {
            finish()
        }
        btn_5k.setOnClickListener {
            val intent = Intent(this,ConfirmTopup::class.java);
            intent.putExtra("Nominal", "5000")
            startActivity(intent)
        }
        btn_10k.setOnClickListener {
            val intent = Intent(this,ConfirmTopup::class.java);
            intent.putExtra("Nominal", "10000")
            startActivity(intent)
        }
        btn_20k.setOnClickListener {
            val intent = Intent(this,ConfirmTopup::class.java);
            intent.putExtra("Nominal", "20000")
            startActivity(intent)
        }
        btn_25k.setOnClickListener {
            val intent = Intent(this,ConfirmTopup::class.java);
            intent.putExtra("Nominal", "25000")
            startActivity(intent)
        }
        btn_50k.setOnClickListener {
            val intent = Intent(this,ConfirmTopup::class.java);
            intent.putExtra("Nominal", "50000")
            startActivity(intent)
        }
        btn_100k.setOnClickListener {
            val intent = Intent(this,ConfirmTopup::class.java);
            intent.putExtra("Nominal", "100000")
            startActivity(intent)
        }
    }
}
