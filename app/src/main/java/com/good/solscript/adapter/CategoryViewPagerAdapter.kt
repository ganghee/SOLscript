package com.good.solscript.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.good.solscript.ui.CategoryContentFragment
import com.good.solscript.ui.CategoryFragment

class CategoryViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val categoryList = listOf("전체", "생필품", "유아", "반려동물")

    override fun getItem(position: Int): Fragment =
        CategoryContentFragment.newInstance(categoryList[position])

    override fun getCount(): Int = categoryList.size

    override fun getPageTitle(position: Int): CharSequence? = categoryList[position]
}
