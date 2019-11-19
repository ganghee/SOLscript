package com.good.solscript.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.good.solscript.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var nowFrag: Fragment

    var homeFragment = HomeFragment()
    var mySubscriptFragment = MySubscriptFragment()
    var categoryFragment = CategoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callFragment(1)

        setClickListener()

    }

    fun setClickListener(){
        rl_mainAct_homeBtn.setOnClickListener {
            callFragment(1)
        }

        rl_mainAct_subscribeBtn.setOnClickListener {
            callFragment(2)
        }

        rl_main_act_categoryBtn.setOnClickListener {
            callFragment(3)
        }

    }

    fun callFragment(frag: Int) {

        when (frag) {
            1 -> {
                nowFrag = homeFragment
            }

            2 -> {
                nowFrag = mySubscriptFragment
            }

            3 -> {
                nowFrag = categoryFragment
            }
        }
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.fl_mainAct_fragContainer, nowFrag)
        transaction.commit()
    }
}
