package com.good.solscript.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.data.SelectedData
import com.good.solscript.databinding.SelectedRecyclerItemBinding

class SelectedAdapter : RecyclerView.Adapter<SelectedAdapter.SelectedViewHolder>() {

    class SelectedViewHolder(val binding : SelectedRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    var data = listOf<SelectedData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.selected_recycler_item,parent,false)

        val viewHolder = SelectedViewHolder(SelectedRecyclerItemBinding.bind(view))

        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.binding.selectedData = data[position]
    }

}