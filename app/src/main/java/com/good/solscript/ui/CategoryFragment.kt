package com.good.solscript.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.good.solscript.R
import com.good.solscript.adapter.CategoryViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("onCreateView","categoryFragment")
        return inflater.inflate(R.layout.fragment_category, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureMainTab()
        Log.d("onActivityCreated","categoryFragment")

    }

    private fun configureMainTab() {
        viewpager_category_item.adapter = CategoryViewPagerAdapter(childFragmentManager)
        tablayout_category_title.setupWithViewPager(viewpager_category_item)
    }
}
