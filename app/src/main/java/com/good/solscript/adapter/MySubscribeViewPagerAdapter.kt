package com.good.solscript.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.good.solscript.ui.CalendarFragment
import com.good.solscript.ui.ManageFragment


class MySubscribeViewPagerAdapter(private var fragNum: Int, fm: FragmentManager) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CalendarFragment()
            1 -> ManageFragment()
            else -> null!!
        }
    }

    // 몇 개의 Fragment인지 결정
    override fun getCount(): Int = fragNum
}