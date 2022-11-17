package com.example.petcareapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.DataClasses.DataClassVacPet
import com.example.petcareapp.R

class VaccineAdapter(private val VaccinesArray:ArrayList<DataClassVacPet>, private val emailUser:String, private val idUser:String): RecyclerView.Adapter<VaccinesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccinesViewHolder {

        val view  = LayoutInflater.from(parent.context)

        return VaccinesViewHolder(view.inflate(R.layout.item_vaccine, parent, false))

    }

    override fun onBindViewHolder(holder: VaccinesViewHolder, position: Int) {
        val item = VaccinesArray.get(position)

        holder.renderVac(item)

    }

    override fun getItemCount(): Int {
    return VaccinesArray.size
    }
}