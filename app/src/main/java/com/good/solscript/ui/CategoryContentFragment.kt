package com.good.solscript.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.adapter.FakeRecyclerViewAdapter
import com.good.solscript.data.FakeRepository
import com.good.solscript.data.FakeResponse
import com.good.solscript.data.SampleRepository
import com.good.solscript.data.SampleData
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_category_content.*

/**
 * A simple [Fragment] subclass.
 */
class CategoryContentFragment : Fragment() {


    private val repository by lazy { SampleRepository() }
    private val fakeList by lazy { mutableListOf<SampleData>() }
    private val fakeAdapter = FakeRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("onCreateView ","CategoryContentFragment")

        return inflater.inflate(R.layout.fragment_category_content, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val categoryName = arguments?.getString(CATEGORY_NAME)

        Log.d("onActivityCreatedLog","")
        recyclerViewSetup()

        repository.getFakeDatas()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("list","fakeList"+fakeList)
                it.map {
                    fakeList.add(it)
                    fakeAdapter.setData(fakeList)
                }
                //fakeList.add(it)

            }, {
                Log.d("fakeList_err", "fail"+it.message)
            })
    }
    private fun recyclerViewSetup() {
        recyclerview_fakelist?.run {
            adapter = fakeAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    companion object {
        private const val CATEGORY_NAME = "category name"
        fun newInstance(categoryName: String): CategoryContentFragment {
            val fragment = CategoryContentFragment()
            val bundle = Bundle()

            bundle.putString(CATEGORY_NAME, categoryName)
            Log.d("category", "" + categoryName)
            fragment.arguments = bundle
            return fragment
        }
    }
}
