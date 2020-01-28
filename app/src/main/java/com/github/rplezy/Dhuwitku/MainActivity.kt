package com.github.rplezy.Dhuwitku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.github.rplezy.Dhuwitku.Adapter.MyPagerAdapter
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.UserModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragment2.*
import kotlinx.android.synthetic.main.header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var firebaseauth : FirebaseAuth
    lateinit var firebaseAuthState : FirebaseAuth.AuthStateListener
    private var data : SharedPreferences? = null

    private val tabIcons =
        intArrayOf(R.drawable.ic_home_black_24dp,
            R.drawable.ic_assignment_black_24dp,
            R.drawable.ic_attach_money24dp)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data = SharedPreferences(applicationContext!!)
        firebaseauth = FirebaseAuth.getInstance()

        firebaseAuthState = FirebaseAuth.AuthStateListener {
            val user = firebaseauth.currentUser
            if (user == null){
                val intent = Intent(this@MainActivity, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }

        viewpager_main!!.adapter = MyPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
        setupTabIcons()
        iv_scan.setOnClickListener{
            val daf = Intent(applicationContext,QrScanner::class.java)
            startActivity(daf)
        }
        iv_more.setOnClickListener{
            val popupMenu: PopupMenu = PopupMenu(this,iv_more)
            popupMenu.menuInflater.inflate(R.menu.optionmenu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item!!.itemId) {
                    R.id.about ->{
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    }
                    R.id.category ->{
                        val i = Intent(this@MainActivity,AddKategori::class.java)
                        startActivity(i)
                    }

                    R.id.logout ->{
                        data!!.session("LOGIN",false)
                        val i = Intent(this@MainActivity,Login::class.java)
                        data!!.clearSharedPreference()
                        finish()
                        startActivity(i)
                    }

                }
                true
            })
            popupMenu.show()
        }
    }

    private fun setupTabIcons() {
        tabs_main.getTabAt(0)?.setIcon(tabIcons[0])
        tabs_main.getTabAt(1)?.setIcon(tabIcons[1])
        tabs_main.getTabAt(2)?.setIcon(tabIcons[2])
    }



}
