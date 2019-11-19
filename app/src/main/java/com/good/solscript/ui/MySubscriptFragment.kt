package com.good.solscript.ui

import android.os.Bundle
<<<<<<< HEAD:app/src/main/java/com/good/solscript/ui/MySubscriptFragment.kt
=======
import androidx.fragment.app.Fragment
>>>>>>> 2515aa7375b0277b20f58c423d2102a34ccae1e7:app/src/main/java/com/good/solscript/ui/mySubscript/MySubscriptFragment.kt
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
        var view = inflater.inflate(R.layout.fragment_my_subscript, container, false)

        configureBottomNavigation(view)

        return view
    }

<<<<<<< HEAD:app/src/main/java/com/good/solscript/ui/MySubscriptFragment.kt
    fun setViewPager(v: View) {

        val tabLayout = v.tl_my_sub_frag_top_btn
        val viewPager: ViewPager = v.vp_my_sub_frag_container

        viewPager.apply {
            adapter = MySubscribeViewPagerAdapter(2, childFragmentManager)
=======
    fun configureBottomNavigation(v : View){

        val tabLayout = v.tl_mysubscriptFrag_top
        val viewPager: ViewPager = v.vp_mysubscriptFrag_container

        viewPager.apply {
            this.adapter = MySubscribeViewPagerAdapter(childFragmentManager)
>>>>>>> 2515aa7375b0277b20f58c423d2102a34ccae1e7:app/src/main/java/com/good/solscript/ui/mySubscript/MySubscriptFragment.kt
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
