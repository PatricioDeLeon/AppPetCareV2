package com.example.petcareapp.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.databinding.ItemPetBinding

class PetsViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val binding = ItemPetBinding.bind(view)
    fun render(petsDataModel:DataClassPets){


        binding.namePet.text = petsDataModel.name_pet
        binding.agePet.text = petsDataModel.age_pet
        binding.racePet.text = petsDataModel.race_pet
        binding.idPet.text = petsDataModel.id

    }


}