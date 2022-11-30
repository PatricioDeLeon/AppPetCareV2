package com.example.petcareapp.Adapters.AdaptersChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassChatList
import com.example.petcareapp.DataClasses.DataClassChats
import com.example.petcareapp.R

class ChatAdapter(private val DataChat:ArrayList<DataClassChatList>):RecyclerView.Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val view  = LayoutInflater.from(parent.context)

        return ChatViewHolder(view.inflate(R.layout.item_chat_master, parent, false))

    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        val item = DataChat.get(position)
        holder.renderChat(item)

    }

    override fun getItemCount(): Int {

        return DataChat.size

    }
}