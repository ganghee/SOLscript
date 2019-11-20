package com.good.solscript.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
import com.good.solscript.data.FakeResponse
=======
import com.good.solscript.data.SampleData
>>>>>>> eb8c9932daa90427a2b8d4273829a25f26247277
import kotlinx.android.synthetic.main.recyclerview_fake_item.view.*


class FakeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    private val body = itemView.textview_body
    private val identify = itemView.textview_id
    private val title = itemView.textview_title
    private val userId = itemView.textview_userId

<<<<<<< HEAD
    fun bind(fakeData: FakeResponse){
=======
    fun bind(fakeData: SampleData){
>>>>>>> eb8c9932daa90427a2b8d4273829a25f26247277
        body.text = fakeData.body
        identify.text = fakeData.id.toString()
        title.text = fakeData.title
        userId.text = fakeData.userId.toString()

    }
}
