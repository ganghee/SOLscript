package com.good.solscript.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.data.FakeResponse
import kotlinx.android.synthetic.main.recyclerview_fake_item.view.*


class FakeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    private val body = itemView.textview_body
    private val identify = itemView.textview_id
    private val title = itemView.textview_title
    private val userId = itemView.textview_userId

    fun bind(fakeData: FakeResponse){
        body.text = fakeData.body
        identify.text = fakeData.id.toString()
        title.text = fakeData.title
        userId.text = fakeData.userId.toString()

    }
}
