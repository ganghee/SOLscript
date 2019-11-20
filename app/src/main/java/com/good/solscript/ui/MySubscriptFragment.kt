package com.good.solscript.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.good.solscript.R
import com.good.solscript.adapter.MySubscribeViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_my_subscript.view.*


/**
 * A simple [Fragment] subclass.
 */
class MySubscriptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_subscript, container, false)

        configureBottomNavigation(view)

        return view
    }
    private fun configureBottomNavigation(v : View){

        Log.d("MySubscriptFragment","")

        val tabLayout = v.tl_mysubscriptFrag_top
        val viewPager: ViewPager = v.vp_mysubscriptFrag_container

        viewPager.apply {
            this.adapter = MySubscribeViewPagerAdapter(childFragmentManager)
        }

        tabLayout.setupWithViewPager(viewPager)

        val bottomNaviLayout: View =
            this.layoutInflater.inflate(R.layout.tablayout_mysubscript_top, null, false)

        tabLayout.getTabAt(0)!!.customView =
            bottomNaviLayout.findViewById(R.id.rl_my_sub_frag_calendar) as RelativeLayout
        tabLayout.getTabAt(1)!!.customView =
            bottomNaviLayout.findViewById(R.id.rl_my_sub_frag_manage) as RelativeLayout

    }
}
