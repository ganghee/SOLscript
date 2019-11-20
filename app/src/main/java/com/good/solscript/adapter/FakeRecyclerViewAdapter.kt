package com.good.solscript.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
<<<<<<< HEAD
import com.good.solscript.data.FakeResponse

class FakeRecyclerViewAdapter : RecyclerView.Adapter<FakeViewHolder>() {

    private val fakeList = mutableListOf<FakeResponse>()
=======
import com.good.solscript.data.SampleData

class FakeRecyclerViewAdapter : RecyclerView.Adapter<FakeViewHolder>() {

    private val fakeList = mutableListOf<SampleData>()
>>>>>>> eb8c9932daa90427a2b8d4273829a25f26247277


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FakeViewHolder =
        FakeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_fake_item,
                parent,
                false
            )
        )

<<<<<<< HEAD
=======

>>>>>>> eb8c9932daa90427a2b8d4273829a25f26247277
    override fun getItemCount(): Int = fakeList.size

    override fun onBindViewHolder(holder: FakeViewHolder, position: Int) =
        holder.bind(fakeList[position])

<<<<<<< HEAD
    fun setData(setDataList: List<FakeResponse>) {
=======
    fun setData(setDataList: List<SampleData>) {
>>>>>>> eb8c9932daa90427a2b8d4273829a25f26247277
        this.fakeList.clear()
        this.fakeList.addAll(setDataList)
        notifyDataSetChanged()
    }
}