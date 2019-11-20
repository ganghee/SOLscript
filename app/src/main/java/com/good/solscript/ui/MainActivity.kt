package com.good.solscript.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.good.solscript.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var nowFrag: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callFragment(1)

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

    private fun callFragment(frag: Int) {

        when (frag) {
            1 -> {
                nowFrag = HomeFragment()
            }

            2 -> {
                nowFrag = MySubscriptFragment()
            }

            3 -> {
                nowFrag = CategoryFragment()
            }
        }

        Log.d("callFragment","  " )

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_mainAct_fragContainer, nowFrag)
        transaction.commit()
    }
}
