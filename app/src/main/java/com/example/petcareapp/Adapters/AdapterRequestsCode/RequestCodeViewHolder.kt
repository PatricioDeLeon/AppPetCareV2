package com.example.petcareapp.Adapters.AdapterRequestsCode

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassRequestCode
import com.example.petcareapp.databinding.ItemRequestCodeBinding

class RequestCodeViewHolder(view: View): RecyclerView.ViewHolder(view)  {


    val binding = ItemRequestCodeBinding.bind(view)

    fun renderRequestsCode(modelData:DataClassRequestCode){

        binding.emailVetReqView.text = modelData.email_vet
        binding.messageReqView.text = modelData.message_req

    }
}