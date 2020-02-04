package com.github.rplezy.Dhuwitku.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.rplezy.Dhuwitku.Fragment.Fragment1
import com.github.rplezy.Dhuwitku.Fragment.Fragment2
import com.github.rplezy.Dhuwitku.Fragment.Fragment3

class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
//    private val pages = listOf(
//        Fragment1(),
//        Fragment2(),
//        Fragment3()
//    )
    private val pages = arrayOf("1", "2", "3")

    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
//        return pages[position]
        return  when (position) {
            0 -> Fragment1()
            1 -> Fragment2()
            2 -> Fragment3()
            else -> Fragment1()
        }
    }

    override fun getCount(): Int {
        return pages.size
    }

    // judul untuk tabs
//    override fun getPageTitle(position: Int): CharSequence? {
//        return when (position) {
//            0 -> "First Tab"
//            else -> "Second Tab"
//        }
//    }
}