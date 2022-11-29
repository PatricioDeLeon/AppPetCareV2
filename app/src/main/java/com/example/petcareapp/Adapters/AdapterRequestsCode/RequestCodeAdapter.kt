package com.example.petcareapp.Adapters.AdapterRequestsCode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.Adapters.VaccinesViewHolder
import com.example.petcareapp.DataClasses.DataClassRequest
import com.example.petcareapp.DataClasses.DataClassRequestCode
import com.example.petcareapp.R

class RequestCodeAdapter(private val requestsArray:ArrayList<DataClassRequestCode>):RecyclerView.Adapter<RequestCodeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestCodeViewHolder {
        val view  = LayoutInflater.from(parent.context)
        return RequestCodeViewHolder(view.inflate(R.layout.item_request_code, parent, false))
    }

    override fun onBindViewHolder(holder: RequestCodeViewHolder, position: Int) {
        val item = requestsArray.get(position)
        holder.renderRequestsCode(item)

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return requestsArray.size

    }

}