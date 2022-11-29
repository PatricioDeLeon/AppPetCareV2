package com.example.petcareapp.Chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.AdaptersChat.MenuChatsUserAdapter
import com.example.petcareapp.Adapters.RequestsOfUsersToVetAdapter
import com.example.petcareapp.DataClasses.DataClassChats
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.MenuActivity
import com.example.petcareapp.databinding.ActivityMenuChatsUserBinding
import com.example.petcareapp.databinding.ActivityRequestUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MenuChatsUserActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase
    val chat = ArrayList<DataClassChats>()
    private lateinit var binding: ActivityMenuChatsUserBinding
    private lateinit var type:String
    private lateinit var name_pet:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //firebse instances
        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        realTime = FirebaseDatabase.getInstance()

        binding = ActivityMenuChatsUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uid = firebaseAuth.currentUser?.uid

        val typeOfUser = getIntent().getExtras()?.getString("typeOfUser")
        type = typeOfUser.toString()

        if(typeOfUser == "user"){

          binding.recyclerViewChatsMenuUser.visibility = View.GONE
            binding.cardUserView.visibility = View.GONE
            realTime.getReference("chats").child(uid.toString()).get().addOnSuccessListener { data ->

                if(data.exists()){
                    binding.cardUserView.visibility = View.VISIBLE
                    binding.nameVetChatView.text = data.child("name_vet").value.toString()
                    binding.emailVetChatView.text = data.child("email_vet").value.toString()
                    name_pet = data.child("name_pet").value.toString()
                }

            }.addOnFailureListener {
                Log.i("Error in get chats user", it.message.toString())
            }

        }else if(typeOfUser == "vet"){

            binding.cardUserView.visibility = View.GONE

            realTime.getReference("chats").get().addOnSuccessListener { dataVet ->
                for(data in dataVet.children){

                    if(uid.toString() == data.child("uid_vet").value.toString()){
                        val dataToShow = DataClassChats(
                            data.child("email_user").value.toString(),
                            data.child("email_vet").value.toString(),
                            data.child("message").value.toString(),
                            data.child("name_user").value.toString(),
                            data.child("name_vet").value.toString(),
                            data.child("uid_vet").value.toString(),
                            data.key.toString(),
                            data.child("name_pet").value.toString(),
                        )

                        chat.add(dataToShow)
                        initRecycleView(chat)

                    }

                }
            }.addOnFailureListener {
                Log.i("Error in get chats user", it.message.toString())
            }

        }

        binding.cardUserView.setOnClickListener {
            val intent = Intent(applicationContext, ChatActivity::class.java)
            intent.putExtra("uid_user", uid)
            intent.putExtra("name_chat", binding.nameVetChatView.text.toString())
            intent.putExtra("typeOfUser", "user")
            intent.putExtra("name_pet", name_pet)
            startActivity(intent)
        }
    }

    fun initRecycleView(data:ArrayList<DataClassChats>){
        binding.recyclerViewChatsMenuUser.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerViewChatsMenuUser.adapter = MenuChatsUserAdapter(data)

    }
}