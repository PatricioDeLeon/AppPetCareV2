package com.example.petcareapp.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassRequest
import com.example.petcareapp.DataClasses.DataClassVetsFireBase
import com.example.petcareapp.databinding.ItemRequestsOfUsersBinding

class RequestsOfUsersViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    val binding = ItemRequestsOfUsersBinding.bind(view)

    fun renderRequests(requestsModel: DataClassRequest){

        binding.nameUserView.text = requestsModel.name_user
        binding.emailUserView.text = requestsModel.email_user
        binding.messageView.text = requestsModel.message

    }
}