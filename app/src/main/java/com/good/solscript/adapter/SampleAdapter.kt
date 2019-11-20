package com.good.solscript.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.data.SampleData
import com.good.solscript.databinding.RecyclerItemBinding

class SampleAdapter : RecyclerView.Adapter<SampleViewHolder>() {

    var data = listOf<SampleData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)

        val viewHolder = SampleViewHolder(RecyclerItemBinding.bind(view))

        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.binding.sampleData = data[position]
    }

}