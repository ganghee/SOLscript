package com.good.solscript.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
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
        var view = inflater.inflate(R.layout.fragment_my_subscript, container, false)

        setViewPager(view)

        return view
    }

    fun setViewPager(v: View) {

        val tabLayout = v.tl_my_sub_frag_top_btn
        val viewPager: ViewPager = v.vp_my_sub_frag_container

        viewPager.apply {
            adapter = MySubscribeViewPagerAdapter(2, childFragmentManager)
        }

        tabLayout.setupWithViewPager(viewPager)

        val bottomNaviLayout: View =
            this.layoutInflater.inflate(R.layout.my_sub_frag_top_tab, null, false)

        tabLayout.getTabAt(0)!!.customView =
            bottomNaviLayout.findViewById(R.id.rl_my_sub_frag_calendar) as RelativeLayout
        tabLayout.getTabAt(1)!!.customView =
            bottomNaviLayout.findViewById(R.id.rl_my_sub_frag_manage) as RelativeLayout

    }
}
