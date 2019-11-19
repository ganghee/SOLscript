package com.good.solscript.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.data.SampleData

class SampleViewHolder(view : View) : RecyclerView.ViewHolder(view){

    val container : View = view.findViewById(R.id.cl_recyclerItem_container)
    val title : TextView = view.findViewById(R.id.tv_recyclerItem_title)
    val contents : TextView = view.findViewById(R.id.tv_recyclerItem_contents)

    fun bind(data : SampleData){
        title.text = data.title
        contents.text = data.body
    }

}