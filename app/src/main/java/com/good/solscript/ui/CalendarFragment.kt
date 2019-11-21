package com.good.solscript.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.good.solscript.R
import com.good.solscript.adapter.SampleAdapter
import com.good.solscript.data.SampleData
import com.good.solscript.data.SampleRepository
import kotlinx.android.synthetic.main.fragment_calendar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {

    val CALENDARFRAGMENT = "calendarFragment:"
    private val repository by lazy { SampleRepository() }
    private val sampleAdapter by lazy { SampleAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("onCreateView","CalendarFragment")
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getSampleResponse()
        setCalendarView()

        tv_calendarfrag_changebtn.setOnClickListener {
            if(tv_calendarfrag_changebtn.text == "리스트로 보기"){
                rv_calendarfrag_samplelist.visibility = View.VISIBLE
                cv_calendarfrag_calendar.visibility = View.GONE
                tv_calendarfrag_changebtn.text = "달력으로 보기"
            }else{
                rv_calendarfrag_samplelist.visibility = View.GONE
                cv_calendarfrag_calendar.visibility = View.VISIBLE
                tv_calendarfrag_changebtn.text = "리스트로 보기"
            }
        }
    }

    private fun getSampleResponse(){
        repository.getSampleDates().enqueue(
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

        rv_calendarfrag_selectedlist.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = sampleAdapter
        }

        rv_calendarfrag_samplelist.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = sampleAdapter
        }
    }

    private fun setCalendarView(){

        cv_calendarfrag_calendar.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(context,"${date.year}년  ${date.month}월 ${date.day}일",Toast.LENGTH_SHORT).show()
            cv_calendarfrag_calendar.setSelectedDate(date)
        }

    }
}
