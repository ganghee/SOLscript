package com.good.solscript.adapter

import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.databinding.RecyclerItemBinding

/*
class SampleViewHolder(view : View) : RecyclerView.ViewHolder(view){

    val container : View = view.findViewById(R.id.cl_recyclerItem_container)
    val title : TextView = view.findViewById(R.id.tv_recyclerItem_title)
    val contents : TextView = view.findViewById(R.id.tv_recyclerItem_contents)

    fun bind(data : SampleData){
        title.text = data.title
        contents.text = data.body
    }

}*/

class SampleViewHolder(val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
