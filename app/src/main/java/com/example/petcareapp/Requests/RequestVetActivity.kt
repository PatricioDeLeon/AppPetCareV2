package com.example.petcareapp.Requests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.RequestsOfUsersToVetAdapter
import com.example.petcareapp.Adapters.VetsToRequestAdapter
import com.example.petcareapp.DataClasses.DataClassRequest
import com.example.petcareapp.DataClasses.DataClassVetsFireBase
import com.example.petcareapp.databinding.ActivityRegisterVetBinding
import com.example.petcareapp.databinding.ActivityRequestUserBinding
import com.example.petcareapp.databinding.ActivityRequestVetBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class RequestVetActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase
    private lateinit var binding: ActivityRequestVetBinding
    val requests = ArrayList<DataClassRequest>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //firebse instances
        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        realTime = FirebaseDatabase.getInstance()

        binding = ActivityRequestVetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT).show()
        realTime.getReference("requests").get().addOnSuccessListener { result ->

            if(result.exists()){
                for (doc in result.children){
                    Log.i("Data -> ", " ${doc.child("uid_vet").value.toString()}")
                    Log.i("Data -> ", " ${doc.child("name_user").value.toString()}")
                    Log.i("Data -> ", " ${doc.child("email_user").value.toString()}")
                    Log.i("Data -> ", " ${doc.child("message_req").value.toString()}")
                    Log.i("Data -> ", " ${doc.key.toString()}")
                    Log.i("Data -> ", " ${doc.child("name_pet").value.toString()}")
                    if(doc.child("uid_vet").value.toString().equals(firebaseAuth.currentUser?.uid)){
                        val dataToAdd = DataClassRequest(
                            doc.child("name_user").value.toString(),
                            doc.child("email_user").value.toString(),
                            doc.child("message_req").value.toString(),
                            doc.key.toString(),
                            doc.child("name_pet").value.toString()

                        )

                        requests.add(dataToAdd)

                    }

                }
                initRecylerView(requests)

            }
        }

    }

    fun initRecylerView(array: ArrayList<DataClassRequest>){
        binding.recyclerReqUsers.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerReqUsers.adapter = RequestsOfUsersToVetAdapter(array)

    }

}