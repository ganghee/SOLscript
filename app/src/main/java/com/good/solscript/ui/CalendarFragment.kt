package com.good.solscript.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.good.solscript.R
import com.good.solscript.adapter.SampleAdapter
import com.good.solscript.data.SampleData
import com.good.solscript.data.remote.SampleRemoteDataSource
import kotlinx.android.synthetic.main.fragment_calendar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {

    val CALENDARFRAGMENT = "calendarFragment:"

    private val call: Call<List<SampleData>> = SampleRemoteDataSource.service.getSample()
    private val sampleAdapter by lazy { SampleAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getSampleResponse()
    }

    private fun getSampleResponse(){
        call.enqueue(
            object : Callback<List<SampleData>> {
                override fun onFailure(call: Call<List<SampleData>>, t: Throwable) {
                    Log.e(CALENDARFRAGMENT, t.toString())
                }

                override fun onResponse(
                    call: Call<List<SampleData>>, response: Response<List<SampleData>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.let { setRecyclerView(it) }
                    }

                }
            }
        )
    }

    fun setRecyclerView(data: List<SampleData>) {

        sampleAdapter.data = data!!
        sampleAdapter.notifyDataSetChanged()

        rv_calendarFrag_sampleList.apply {
            adapter = sampleAdapter
            layoutManager = LinearLayoutManager(activity?.applicationContext)
        }
    }
}
