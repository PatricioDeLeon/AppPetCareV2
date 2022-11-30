package com.example.petcareapp.Chat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.AdaptersChat.ChatAdapter
import com.example.petcareapp.Adapters.AdaptersChat.MenuChatsUserAdapter
import com.example.petcareapp.DataClasses.DataClassChatList
import com.example.petcareapp.databinding.ActivityChatBinding
import com.example.petcareapp.databinding.ActivityRequestVetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase
    private lateinit var binding: ActivityChatBinding
    val chatList = ArrayList<DataClassChatList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //firebse instances
        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        realTime = FirebaseDatabase.getInstance()

        val uid_user = getIntent().getExtras()?.getString("uid_user")
        val name_chat = getIntent().getExtras()?.getString("name_chat")
        val name_pet = getIntent().getExtras()?.getString("name_pet")
         val typeOfUser = getIntent().getExtras()?.getString("typeOfUser")

        val uid_logged = firebaseAuth.currentUser?.uid

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameChat.text = "$name_chat -> $name_pet"


        val eventListener = object : ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                val dataSent = DataClassChatList(
                    snapshot.child("who_sent").value.toString(),
                    snapshot.child("text_message").value.toString(),
                    snapshot.child("time_stamp").value.toString()
                )

                chatList.add(dataSent)
                initRecycleView(chatList)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        realTime.getReference("chats").child(uid_user.toString()).child("chat").orderByChild("time_stamp").addChildEventListener(eventListener)

        binding.sendMessage.setOnClickListener {

            if(!binding.messageText.text.toString().isNullOrEmpty()){

                val newMessage = hashMapOf(
                    "who_sent" to uid_logged.toString(),
                    "text_message" to binding.messageText.text.toString(),
                    "time_stamp" to DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(
                                    ZoneOffset.systemDefault()).format( Instant.now()).toString()
                )

                realTime.getReference("chats")
                    .child(uid_user.toString())
                    .child("chat")
                    .child(getSaltString()).setValue(newMessage).addOnFailureListener {
                        Log.i("Error sent message", it.message.toString())
                    }

                binding.messageText.text = null
            }

        }

        binding.goMenuChats.setOnClickListener {

            val intent = Intent(applicationContext, MenuChatsUserActivity::class.java)
            intent.putExtra("typeOfUser", typeOfUser)
            finish()
            startActivity(intent)

        }

    }

    protected fun getSaltString(): String {
        val SALTCHARS = "1234567890"
        val salt = StringBuilder()
        val rnd = Random()
        while (salt.length < 12) { // length of the random string.
            val index = (rnd.nextFloat() * SALTCHARS.length).toInt()
            salt.append(SALTCHARS[index])
        }
        return salt.toString()
    }

    fun initRecycleView(data:ArrayList<DataClassChatList>){

        binding.recyclerViewChatMaster.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerViewChatMaster.adapter = ChatAdapter(data)
        binding.recyclerViewChatMaster.scrollToPosition(binding.recyclerViewChatMaster.adapter?.itemCount!!.minus(1))

    }
}