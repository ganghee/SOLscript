package com.good.solscript.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.good.solscript.R
import com.good.solscript.adapter.ChatAdapter
import com.good.solscript.data.ChatData
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private val chatAdapter by lazy { ChatAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setChatRecyclerView()

        tv_chatact_sendbtn.setOnClickListener { sendTextMessage() }
    }

    private fun setChatRecyclerView(){

        var dataList = arrayListOf<ChatData>()

        dataList.add(ChatData("안녕 ㅋㅋㅋ","0",true))
        dataList.add(ChatData("안녕하세요~","1",false))
        dataList.add(ChatData("좋은하루에용","1",false))
        dataList.add(ChatData("파이팅!","2",true))

        chatAdapter.data = dataList!!
        chatAdapter.notifyDataSetChanged()

        rv_chatact_chatlist.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = chatAdapter
        }
    }

    private fun sendTextMessage(){

        var message = et_chatact_inputtext.text.toString()
        chatAdapter.addItem(ChatData(message,"",me = true))
        et_chatact_inputtext.setText("")
    }
}
