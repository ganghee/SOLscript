package com.good.solscript.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.data.SelectedData
import com.good.solscript.databinding.RecyclerSelectedItemBinding

class SelectedAdapter : RecyclerView.Adapter<SelectedAdapter.SelectedViewHolder>() {

    class SelectedViewHolder(val binding : RecyclerSelectedItemBinding) : RecyclerView.ViewHolder(binding.root)

    var data = listOf<SelectedData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_selected_item,parent,false)
        return SelectedViewHolder(RecyclerSelectedItemBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.binding.selectedData = data[position]
    }

}