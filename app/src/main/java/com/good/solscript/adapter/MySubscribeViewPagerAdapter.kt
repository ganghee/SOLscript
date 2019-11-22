package com.good.solscript.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.good.solscript.ui.SubscriptCalendarFragment
import com.good.solscript.ui.SubscriptManageFragment

class MySubscribeViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragList = listOf("calendar", "manage")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SubscriptCalendarFragment()
            1 -> SubscriptManageFragment()
            else -> null!!
        }
    }

    override fun getCount(): Int = fragList.size
    override fun getPageTitle(position: Int): CharSequence? = fragList[position]
}