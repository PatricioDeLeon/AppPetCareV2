package com.example.petcareapp.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassVacPet
import com.example.petcareapp.databinding.ItemVaccineBinding

class VaccinesViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemVaccineBinding.bind(view)

    fun renderVac(vaccineDataModel: DataClassVacPet){
        val id_vac = vaccineDataModel.id_vac
        val id_pet = vaccineDataModel.id_pet
        val id_user = vaccineDataModel.id_user
        binding.namePet.text = vaccineDataModel.name_pet
        binding.racePet.text = vaccineDataModel.race_pet
        binding.vaccineVac.text = vaccineDataModel.vaccine_vac
        binding.messageVac.text = vaccineDataModel.message_vac
        binding.date.text = vaccineDataModel.date_vac
    }
}