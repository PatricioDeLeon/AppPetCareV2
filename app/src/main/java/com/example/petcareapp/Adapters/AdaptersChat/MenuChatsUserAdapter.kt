package com.example.petcareapp.Adapters.AdaptersChat

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.Adapters.RequestsOfUsersViewHolder
import com.example.petcareapp.Chat.ChatActivity
import com.example.petcareapp.DataClasses.DataClassChats
import com.example.petcareapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MenuChatsUserAdapter(private val DataArray:ArrayList<DataClassChats>):RecyclerView.Adapter<MenuChatUserViewHolder>() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuChatUserViewHolder {
        val view  = LayoutInflater.from(parent.context)

        return MenuChatUserViewHolder(view.inflate(R.layout.item_menu_chat_vet, parent, false))

    }

    override fun onBindViewHolder(holder: MenuChatUserViewHolder, position: Int) {
        realTime = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        val item = DataArray.get(position)
        holder.renderChatMenuVet(item)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ChatActivity::class.java)
            intent.putExtra("uid_user", item.key)
            intent.putExtra("name_chat", item.name_user)
            intent.putExtra("typeOfUser", "vet")
            intent.putExtra("name_pet", item.name_pet)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return DataArray.size
    }
}