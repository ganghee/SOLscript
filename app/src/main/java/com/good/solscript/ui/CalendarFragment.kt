package com.good.solscript.ui


import android.graphics.Color
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
import com.good.solscript.adapter.SelectedAdapter
import com.good.solscript.data.SampleData
import com.good.solscript.data.SampleRepository
import com.good.solscript.data.SelectedData
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_calendar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {

    val CALENDARFRAGMENT = "calendarFragment:"
    private val repository by lazy { SampleRepository() }
    private val sampleAdapter by lazy { SampleAdapter() }

    private val selectedAdapter by lazy { SelectedAdapter() }


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
                cl_calendarfrag_containier.visibility = View.GONE
                tv_calendarfrag_changebtn.text = "달력으로 보기"
            }else{
                rv_calendarfrag_samplelist.visibility = View.GONE
                cl_calendarfrag_containier.visibility = View.VISIBLE
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

        rv_calendarfrag_samplelist.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = sampleAdapter
        }
    }

    private fun setCalendarView(){

        var dates: ArrayList<CalendarDay> = ArrayList()

        cv_calendarfrag_calendar.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(context,"${date.year}년  ${date.month}월 ${date.day}일",Toast.LENGTH_SHORT).show()

            rv_calendarfrag_selectedlist.visibility = View.VISIBLE
            setCalendarSelectedRecyclerView(date)

            dates.add(CalendarDay.from(date.year,date.month,date.day))
            cv_calendarfrag_calendar.addDecorator(EventDecorator(Color.MAGENTA, dates))
        }

    }

    private fun setCalendarSelectedRecyclerView( date : CalendarDay){

        var dataList = arrayListOf<SelectedData>()
        dataList.add(SelectedData("Netflix","${date.year}년  ${date.month}월 ${date.day}일","0","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJcAAACXCAMAAAAvQTlLAAAAclBMVEX////lCRTkAAD3xsflAA7lAAnsbnH4uLztV17pSE3+7u7xgYbyiI3mJSr0r7DsSVH0pKftXmTnKjH6y87529v+9/fwe4DnGSTmEBr96er509T4zs/0n6L3vb/sUVfsRU3tZmrxkZToOj/qMzvwdXrzmJuT1WQdAAADtklEQVR4nO2YjY6iMBRGoZTRQWEUBQqKosj7v+L2loJlMLazu2STzXdiMtdOgUN/bls9DwAAAAAAAAAAAAAAAAAAAAAAAAAAgP+PgIhUWFJYjdFIlXnZaVISnGZFpXn9QDSU9JX1/yp1gcWLM0lf6UxhQ9GKmawjr/yclLDPyIvuk5Kz5yX09zJ5aSr5Iq+contGhVk6lL4j5r7PchXuQhl+9F7+E7aXXpfQN4ukavRpFvEdeVHlqRfVpagW3OdipUK6OTs4eHE/WtorWlOYU4PRdeE1c/Dy2eqFFw97ei8mI+7rYnYZvIY6zOLldTLk6VGOLkE9lFi0tNdl7sWL65fiupESOX1JqW1TKpIvrrzE/UuzsniVqvek/VYG4f3o5MXFcebFmllT0y3ZVn8hL56a0++tl0cdGbbe8R4aN7F4+eF57rWPvtfdzL3M4fveK1ANVlElLmyjfvBSjbOsV6Qaar+nZrtatbQXL4KlvWR6pI5Rw7l29fJZt7jXKeU6p6S2JPH0CmUuto77F14ndy+v0VmR1gZXL5+dvueJe9d1t+1m9cbL9+OU6Ke9zSvpvXhrTRKm12OWV/uF7/HWixOhqFy8sqKfY7mDls5f8hO/XofMu7zw6lugcPLyHup1xcpzgLzEVyFHWJIs7aVyvkuS0F7hueVSYMd/3I+6DnPzOiivyU7orRc7rEM//PwQk3GffuSb7XZj5Jr5fIyToyJz8rqo141dhn3vFagGaotl88SpHx3s5u6VsWFaLpdXvVx7rUtnL2/PFvdSOwmVWsxGtnjVy3udh0nulMC01/AyC+4nWnqCeG7bnbzGzjfG/d/1UlOLdW676KdXMu/HY1RKquAwTEwHLx43H/m2263UmdT06jfh2eMH+y/yilo+9eLxPRXD+dHZa8y06ihjeAV6f19TNhb2kT946Rlp5nuJKtr/xGtcv+qp10Ofh460TDiksNGr/u41PsHw4vzpdZWNErt6HdRz6NobdeTFmsJGr0zwuReno+HYj1sRp0Wnv2Sbpmke5pqSsLETJVOvmxoZ9Bbq/Bhad9KjV78LGbw0RXtp8mQY92UlGd80iyTmraITEdRJt82b68HTv0/QOl3G1NT9in2ljsxdztu914mxUAiV86r8tqvJwGEj/o7sWK12NAGStI2FPm6oaRvaUlgc6jb3ytvtnAQua9fvUEpDLVvo33/e4sfr/LyUzGuq+ta0raVSlv1hX/0e/+apAAAAAAAAAAAAAAAAAAAAAAAAAADgv+EXdt8/epX0z40AAAAASUVORK5CYII="))
        dataList.add(SelectedData("YoutubePremium","${date.year}년  ${date.month}월 ${date.day}일","1","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJcAAACXCAMAAAAvQTlLAAAAclBMVEX////lCRTkAAD3xsflAA7lAAnsbnH4uLztV17pSE3+7u7xgYbyiI3mJSr0r7DsSVH0pKftXmTnKjH6y87529v+9/fwe4DnGSTmEBr96er509T4zs/0n6L3vb/sUVfsRU3tZmrxkZToOj/qMzvwdXrzmJuT1WQdAAADtklEQVR4nO2YjY6iMBRGoZTRQWEUBQqKosj7v+L2loJlMLazu2STzXdiMtdOgUN/bls9DwAAAAAAAAAAAAAAAAAAAAAAAAAAgP+PgIhUWFJYjdFIlXnZaVISnGZFpXn9QDSU9JX1/yp1gcWLM0lf6UxhQ9GKmawjr/yclLDPyIvuk5Kz5yX09zJ5aSr5Iq+contGhVk6lL4j5r7PchXuQhl+9F7+E7aXXpfQN4ukavRpFvEdeVHlqRfVpagW3OdipUK6OTs4eHE/WtorWlOYU4PRdeE1c/Dy2eqFFw97ei8mI+7rYnYZvIY6zOLldTLk6VGOLkE9lFi0tNdl7sWL65fiupESOX1JqW1TKpIvrrzE/UuzsniVqvek/VYG4f3o5MXFcebFmllT0y3ZVn8hL56a0++tl0cdGbbe8R4aN7F4+eF57rWPvtfdzL3M4fveK1ANVlElLmyjfvBSjbOsV6Qaar+nZrtatbQXL4KlvWR6pI5Rw7l29fJZt7jXKeU6p6S2JPH0CmUuto77F14ndy+v0VmR1gZXL5+dvueJe9d1t+1m9cbL9+OU6Ke9zSvpvXhrTRKm12OWV/uF7/HWixOhqFy8sqKfY7mDls5f8hO/XofMu7zw6lugcPLyHup1xcpzgLzEVyFHWJIs7aVyvkuS0F7hueVSYMd/3I+6DnPzOiivyU7orRc7rEM//PwQk3GffuSb7XZj5Jr5fIyToyJz8rqo141dhn3vFagGaotl88SpHx3s5u6VsWFaLpdXvVx7rUtnL2/PFvdSOwmVWsxGtnjVy3udh0nulMC01/AyC+4nWnqCeG7bnbzGzjfG/d/1UlOLdW676KdXMu/HY1RKquAwTEwHLx43H/m2263UmdT06jfh2eMH+y/yilo+9eLxPRXD+dHZa8y06ihjeAV6f19TNhb2kT946Rlp5nuJKtr/xGtcv+qp10Ofh460TDiksNGr/u41PsHw4vzpdZWNErt6HdRz6NobdeTFmsJGr0zwuReno+HYj1sRp0Wnv2Sbpmke5pqSsLETJVOvmxoZ9Bbq/Bhad9KjV78LGbw0RXtp8mQY92UlGd80iyTmraITEdRJt82b68HTv0/QOl3G1NT9in2ljsxdztu914mxUAiV86r8tqvJwGEj/o7sWK12NAGStI2FPm6oaRvaUlgc6jb3ytvtnAQua9fvUEpDLVvo33/e4sfr/LyUzGuq+ta0raVSlv1hX/0e/+apAAAAAAAAAAAAAAAAAAAAAAAAAADgv+EXdt8/epX0z40AAAAASUVORK5CYII="))
        dataList.add(SelectedData("YoutubePremium","${date.year}년  ${date.month}월 ${date.day}일","2","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJcAAACXCAMAAAAvQTlLAAAAclBMVEX////lCRTkAAD3xsflAA7lAAnsbnH4uLztV17pSE3+7u7xgYbyiI3mJSr0r7DsSVH0pKftXmTnKjH6y87529v+9/fwe4DnGSTmEBr96er509T4zs/0n6L3vb/sUVfsRU3tZmrxkZToOj/qMzvwdXrzmJuT1WQdAAADtklEQVR4nO2YjY6iMBRGoZTRQWEUBQqKosj7v+L2loJlMLazu2STzXdiMtdOgUN/bls9DwAAAAAAAAAAAAAAAAAAAAAAAAAAgP+PgIhUWFJYjdFIlXnZaVISnGZFpXn9QDSU9JX1/yp1gcWLM0lf6UxhQ9GKmawjr/yclLDPyIvuk5Kz5yX09zJ5aSr5Iq+contGhVk6lL4j5r7PchXuQhl+9F7+E7aXXpfQN4ukavRpFvEdeVHlqRfVpagW3OdipUK6OTs4eHE/WtorWlOYU4PRdeE1c/Dy2eqFFw97ei8mI+7rYnYZvIY6zOLldTLk6VGOLkE9lFi0tNdl7sWL65fiupESOX1JqW1TKpIvrrzE/UuzsniVqvek/VYG4f3o5MXFcebFmllT0y3ZVn8hL56a0++tl0cdGbbe8R4aN7F4+eF57rWPvtfdzL3M4fveK1ANVlElLmyjfvBSjbOsV6Qaar+nZrtatbQXL4KlvWR6pI5Rw7l29fJZt7jXKeU6p6S2JPH0CmUuto77F14ndy+v0VmR1gZXL5+dvueJe9d1t+1m9cbL9+OU6Ke9zSvpvXhrTRKm12OWV/uF7/HWixOhqFy8sqKfY7mDls5f8hO/XofMu7zw6lugcPLyHup1xcpzgLzEVyFHWJIs7aVyvkuS0F7hueVSYMd/3I+6DnPzOiivyU7orRc7rEM//PwQk3GffuSb7XZj5Jr5fIyToyJz8rqo141dhn3vFagGaotl88SpHx3s5u6VsWFaLpdXvVx7rUtnL2/PFvdSOwmVWsxGtnjVy3udh0nulMC01/AyC+4nWnqCeG7bnbzGzjfG/d/1UlOLdW676KdXMu/HY1RKquAwTEwHLx43H/m2263UmdT06jfh2eMH+y/yilo+9eLxPRXD+dHZa8y06ihjeAV6f19TNhb2kT946Rlp5nuJKtr/xGtcv+qp10Ofh460TDiksNGr/u41PsHw4vzpdZWNErt6HdRz6NobdeTFmsJGr0zwuReno+HYj1sRp0Wnv2Sbpmke5pqSsLETJVOvmxoZ9Bbq/Bhad9KjV78LGbw0RXtp8mQY92UlGd80iyTmraITEdRJt82b68HTv0/QOl3G1NT9in2ljsxdztu914mxUAiV86r8tqvJwGEj/o7sWK12NAGStI2FPm6oaRvaUlgc6jb3ytvtnAQua9fvUEpDLVvo33/e4sfr/LyUzGuq+ta0raVSlv1hX/0e/+apAAAAAAAAAAAAAAAAAAAAAAAAAADgv+EXdt8/epX0z40AAAAASUVORK5CYII="))

        selectedAdapter.data = dataList!!
        selectedAdapter.notifyDataSetChanged()

        rv_calendarfrag_selectedlist.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = selectedAdapter
        }
    }
}
