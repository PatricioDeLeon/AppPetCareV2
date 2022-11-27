package com.example.petcareapp.Adapters

import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassVetsFireBase
import com.example.petcareapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class VetsToRequestAdapter(private val vetsArray:ArrayList<DataClassVetsFireBase>): RecyclerView.Adapter<VetsRequestViewHolder>() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var realTime: FirebaseDatabase
    private lateinit var fireStore: FirebaseFirestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VetsRequestViewHolder {
        val view  = LayoutInflater.from(parent.context)

        return VetsRequestViewHolder(view.inflate(R.layout.item_vet_to_request, parent, false))
    }

    override fun onBindViewHolder(holder: VetsRequestViewHolder, position: Int) {

        firebaseAuth = FirebaseAuth.getInstance()
        realTime = FirebaseDatabase.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        val item = vetsArray.get(position)
        holder.render(item)

        holder.itemView.setOnClickListener {

            val builder = Dialog(holder.itemView.context)
            builder.setContentView(R.layout.dialog_send_req)
            val btnAccept = builder.findViewById<Button>(R.id.btnAcceptRequest)
            val btnCancel = builder.findViewById<Button>(R.id.btnCancelRequest)
            btnCancel.setOnClickListener { builder.dismiss() }
            btnAccept.setOnClickListener {
                val message = builder.findViewById<EditText>(R.id.mesage_request).text.toString()
                val userUid = firebaseAuth.currentUser?.uid
                Toast.makeText(holder.itemView.context, "$userUid -> ${item.uid_vet}", Toast.LENGTH_SHORT).show()

                fireStore.collection("usuarios").document(userUid.toString()).get().addOnSuccessListener { document ->

                    val valueToSave = hashMapOf(
                        "message_req" to message,
                        "name_user" to document.get("name"),
                        "email_user" to document.get("email"),
                        "uid_vet" to item.uid_vet
                    )

                    realTime.getReference("requests").child(userUid.toString()).setValue(valueToSave).addOnSuccessListener {
                        Toast.makeText(holder.itemView.context, "Peticion enviada: $valueToSave", Toast.LENGTH_SHORT).show()
                        builder.dismiss()
                    }.addOnFailureListener {
                        Log.i("TAG", "Error al obtener documentos $it")
                    }
                }.addOnFailureListener {
                    Log.i("TAG", "Error al obtener documentos $it")
                }
            }
            builder.show()

        }
    }

    override fun getItemCount(): Int {
        return vetsArray.size
    }


}