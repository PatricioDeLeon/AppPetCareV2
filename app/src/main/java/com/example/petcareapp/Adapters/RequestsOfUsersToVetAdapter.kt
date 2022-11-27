package com.example.petcareapp.Adapters

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassRequest
import com.example.petcareapp.DataClasses.DataClassVetsFireBase
import com.example.petcareapp.R

class RequestsOfUsersToVetAdapter(private val requestsArray:ArrayList<DataClassRequest>):RecyclerView.Adapter<RequestsOfUsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsOfUsersViewHolder {
        val view  = LayoutInflater.from(parent.context)

        return RequestsOfUsersViewHolder(view.inflate(R.layout.item_requests_of_users, parent, false))
    }

    override fun onBindViewHolder(holder: RequestsOfUsersViewHolder, position: Int) {

        val item = requestsArray.get(position)
        holder.renderRequests(item)
        holder.itemView.setOnClickListener {

            val builder = Dialog(holder.itemView.context)
            builder.setContentView(R.layout.dialog_confirm_req)
            val btnAccept = builder.findViewById<Button>(R.id.btnAccept)
            val btnDelete = builder.findViewById<Button>(R.id.btnDeleteRequest)
            val btnCancel = builder.findViewById<Button>(R.id.btnCancelRequest)
            btnCancel.setOnClickListener { builder.dismiss() }
            btnDelete.setOnClickListener {

            }
            btnAccept.setOnClickListener {

            }

        }

    }

    override fun getItemCount(): Int {
        return requestsArray.size
    }


}