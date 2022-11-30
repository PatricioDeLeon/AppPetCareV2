package com.example.petcareapp.Adapters.AdaptersChat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassChats
import com.example.petcareapp.databinding.ItemMenuChatUserBinding
import com.example.petcareapp.databinding.ItemMenuChatVetBinding
import com.example.petcareapp.databinding.ItemRequestsOfUsersBinding

class MenuChatUserViewHolder(view: View): RecyclerView.ViewHolder(view)  {





    val binding2 = ItemMenuChatVetBinding.bind(view)


    fun renderChatMenuVet(chatModel:DataClassChats){


            binding2.nameUserChatView.text = chatModel.name_user
            binding2.emailUserChatView.text = chatModel.email_user


    }
}