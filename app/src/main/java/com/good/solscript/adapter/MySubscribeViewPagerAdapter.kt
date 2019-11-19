package com.good.solscript.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.good.solscript.ui.CalendarFragment
import com.good.solscript.ui.ManageFragment

class MySubscribeViewPagerAdapter(var fragNum : Int, fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    var calendarFragment = CalendarFragment()
    var manageFragment = ManageFragment()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> calendarFragment
            1 -> manageFragment
            else -> null!!
        }
    }

    // 몇 개의 Fragment인지 결정
    override fun getCount(): Int = fragNum
}