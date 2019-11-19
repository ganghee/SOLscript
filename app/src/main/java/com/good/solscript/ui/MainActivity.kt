package com.good.solscript.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.good.solscript.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var nowFrag: Fragment

    private val homeFragment = HomeFragment()
    private val mySubscriptFragment = MySubscriptFragment()
    private val categoryFragment = CategoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callFragment(1)


        rl_main_act_tab_btn_home.setOnClickListener {
            callFragment(1)
        }

        rl_main_act_tab_btn_subscribe.setOnClickListener {
            callFragment(2)
        }

        rl_main_act_tab_btn_category.setOnClickListener {
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
        transaction.replace(R.id.fl_main_act_frag_container, nowFrag)
        transaction.commit()
    }
}
