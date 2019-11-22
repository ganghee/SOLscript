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
        setClickListener()

    }

    fun setClickListener(){
        rl_mainact_homebtn.setOnClickListener {
            callFragment(1)
        }

        rl_mainact_subscribebtn.setOnClickListener {
            callFragment(2)
        }

        rl_mainact_categorybtn.setOnClickListener {
            callFragment(3)
        }

    }

    private fun callFragment(frag: Int) {

        when (frag) {
            1 -> {
                nowFrag = HomeFragment()
            }

            2 -> {
                nowFrag = SubscriptFragment()
            }

            3 -> {
                nowFrag = CategoryFragment()
            }
        }

        Log.d("callFragment", "  ")

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_mainact_container, nowFrag)
        transaction.commit()
    }
}
