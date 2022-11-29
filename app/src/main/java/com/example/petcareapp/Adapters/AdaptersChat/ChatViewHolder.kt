package com.example.petcareapp.Adapters.AdaptersChat

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassChatList
import com.example.petcareapp.databinding.ItemChatMasterBinding
import com.example.petcareapp.databinding.ItemMenuChatVetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemChatMasterBinding.bind(view)

    private lateinit var firebaseAuth: FirebaseAuth




    fun renderChat(dataModel:DataClassChatList){

        firebaseAuth = FirebaseAuth.getInstance()

        val uid = firebaseAuth.currentUser?.uid
        val layout =  binding.llMainMessageLayout



        if(uid == dataModel.who_sent){
            layout.gravity = Gravity.RIGHT
        }else{
            layout.gravity = Gravity.LEFT
        }
        binding.textMessage.text = dataModel.text_message
        binding.time.text = dataModel.time_stamp




    }




}