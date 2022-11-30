package com.example.petcareapp.Api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.Adapters.AdaptersChat.ChatViewHolder
import com.example.petcareapp.DataClasses.DataClassChatList
import com.example.petcareapp.R
import com.squareup.picasso.Picasso

class ApiRestAdapter(private val dataImage:ArrayList<String>):RecyclerView.Adapter<ApiRestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiRestViewHolder {

        val view  = LayoutInflater.from(parent.context)

        return ApiRestViewHolder(view.inflate(R.layout.item_image_api, parent, false))

    }

    override fun onBindViewHolder(holder: ApiRestViewHolder, position: Int) {
            val item = dataImage.get(position)
        holder.renderImage(item)

    }

    override fun getItemCount(): Int {
        return dataImage.size
    }
}