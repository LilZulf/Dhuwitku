package com.github.rplezy.Dhuwitku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        //setSupportActionBar(toolbar)

        init()
    }
    private fun init() {
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when(position) {
                    0 -> {
                        BlankFragment.newInstance()
                    }
                    else -> {
                        TemplateFragment.newInstance()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 2
            }
        }

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Size"
                else -> "Template"
            }
        }.attach()
    }
}


