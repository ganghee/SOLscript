package com.good.solscript.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.good.solscript.ui.CalendarFragment
import com.good.solscript.ui.ManageFragment

class MySubscribeViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragList = listOf("calendar", "manage")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CalendarFragment()
            1 -> ManageFragment()
            else -> null!!
        }
    }

    override fun getCount(): Int = fragList.size
    override fun getPageTitle(position: Int): CharSequence? = fragList[position]
}