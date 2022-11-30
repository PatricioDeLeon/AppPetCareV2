package com.example.petcareapp.Requests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.PetsAdapter
import com.example.petcareapp.Adapters.VetsToRequestAdapter
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.DataClasses.DataClassVacPet
import com.example.petcareapp.DataClasses.DataClassVetsFireBase
import com.example.petcareapp.databinding.ActivityRequestUserBinding
import com.example.petcareapp.databinding.LoginActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class RequestUserActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase
    private lateinit var binding: ActivityRequestUserBinding

    val vetsList = ArrayList<DataClassVetsFireBase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //firebse instances
        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        realTime = FirebaseDatabase.getInstance()

        binding = ActivityRequestUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT).show()

        val userUid = firebaseAuth.currentUser?.uid
        realTime.getReference("requests").child(userUid.toString()).get().addOnSuccessListener { result ->

            if(result.exists()){

                Toast.makeText(applicationContext, "Requests already senrt", Toast.LENGTH_SHORT).show()

            }else{

                realTime.getReference("chats").child(userUid.toString()).get().addOnSuccessListener { chat ->

                    if(chat.exists()){
                        Toast.makeText(applicationContext, "Chat already exists", Toast.LENGTH_SHORT).show()
                    }else{

                        fireStore.collection("veterinarios").get().addOnSuccessListener { result ->

                            if(result.isEmpty){
                                Toast.makeText(applicationContext, "Theres no data", Toast.LENGTH_SHORT).show()
                            }else{
                                for (doc in result){
                                    Log.i("Data -> ", " ${doc.id} => ${doc.get("email_vet")}")
                                    val dataToAdd = DataClassVetsFireBase(
                                        doc.id,
                                        doc.get("name_vet").toString(),
                                        doc.get("email_vet").toString(),
                                        doc.get("phone_vet").toString()
                                    )
                                    vetsList.add(dataToAdd)
                                }

                                initRecyclerView(vetsList)
                            }

                        }.addOnFailureListener {
                            Log.i("TAG", "Error al obtener documentos $it")
                        }

                    }

                }



            }


        }.addOnFailureListener {

            Log.i("TAG", "Error al obtener documentos $it")

        }





    }

    fun initRecyclerView(vetList: ArrayList<DataClassVetsFireBase>){
        binding.recyclerVets.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerVets.adapter = VetsToRequestAdapter(vetList)

    }
}