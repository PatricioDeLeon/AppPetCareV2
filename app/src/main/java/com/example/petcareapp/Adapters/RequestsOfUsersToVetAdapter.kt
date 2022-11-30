package com.example.petcareapp.Adapters

import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassRequest
import com.example.petcareapp.DataClasses.DataClassVetsFireBase
import com.example.petcareapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class RequestsOfUsersToVetAdapter(private val requestsArray:ArrayList<DataClassRequest>):RecyclerView.Adapter<RequestsOfUsersViewHolder>() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsOfUsersViewHolder {
        val view  = LayoutInflater.from(parent.context)

        return RequestsOfUsersViewHolder(view.inflate(R.layout.item_requests_of_users, parent, false))
    }

    override fun onBindViewHolder(holder: RequestsOfUsersViewHolder, position: Int) {
        realTime = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        val item = requestsArray.get(position)
        val uid_vet = firebaseAuth.currentUser?.uid
        holder.renderRequests(item)


        holder.itemView.setOnClickListener {

            val builder = Dialog(holder.itemView.context)
            builder.setContentView(R.layout.dialog_confirm_req)
            val btnAccept = builder.findViewById<Button>(R.id.btnAccept)
            val btnDelete = builder.findViewById<Button>(R.id.btnDeleteRequest)
            val btnClose = builder.findViewById<Button>(R.id.closeDialog)


            btnClose.setOnClickListener { builder.dismiss() }

            btnDelete.setOnClickListener {

                realTime.getReference("requests").child(item.key).removeValue().addOnSuccessListener {
                    Toast.makeText(holder.itemView.context,"Request Deleted" , Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Log.i("Error trying delete req", it.message.toString())
                }

            }
            btnAccept.setOnClickListener {

                fireStore.collection("veterinarios").document(uid_vet.toString()).get().addOnSuccessListener { data ->

                    val valueToSave = hashMapOf(
                        "uid_vet" to uid_vet,
                        "name_vet" to data.get("name_vet").toString(),
                        "email_vet" to data.get("email_vet").toString(),
                        "name_user" to item.name_user,
                        "email_user" to item.email_user,
                        "message" to item.message,
                        "name_pet" to item.name_pet
                    )

                    realTime.getReference("chats").child(item.key).setValue(valueToSave).addOnSuccessListener {

                        realTime.getReference("requests").child(item.key).removeValue().addOnSuccessListener {

                            Toast.makeText(holder.itemView.context,"Requests Accept" , Toast.LENGTH_SHORT).show()

                        }.addOnFailureListener {

                            Log.i("Error trying delete req", it.message.toString())
                        }

                    }.addOnFailureListener {
                        Log.i("Error trying save chats", it.message.toString())

                    }


                }.addOnFailureListener {
                    Log.i("Error get data vet", it.message.toString())
                }



            }
            builder.show()

        }

    }

    override fun getItemCount(): Int {
        return requestsArray.size
    }


}