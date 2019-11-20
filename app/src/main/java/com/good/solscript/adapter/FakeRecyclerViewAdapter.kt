package com.good.solscript.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.data.SampleData

class FakeRecyclerViewAdapter : RecyclerView.Adapter<FakeViewHolder>() {

    private val fakeList = mutableListOf<SampleData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FakeViewHolder =
        FakeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_fake_item,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = fakeList.size

    override fun onBindViewHolder(holder: FakeViewHolder, position: Int) =
        holder.bind(fakeList[position])

    fun setData(setDataList: List<SampleData>) {
        this.fakeList.clear()
        this.fakeList.addAll(setDataList)
        notifyDataSetChanged()
    }
}