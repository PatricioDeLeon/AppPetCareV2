package com.example.petcareapp.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassVetsFireBase
import com.example.petcareapp.databinding.ItemVaccineBinding
import com.example.petcareapp.databinding.ItemVetToRequestBinding

class VetsRequestViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemVetToRequestBinding.bind(view)

    fun render(vetsRequestModel:DataClassVetsFireBase){
        binding.nameVetView.text = vetsRequestModel.name_vet
        binding.emailVetView.text = vetsRequestModel.email_vet
        binding.phoneVetView.text = vetsRequestModel.phone_vet
        binding.uidVet.text = vetsRequestModel.uid_vet

    }
}