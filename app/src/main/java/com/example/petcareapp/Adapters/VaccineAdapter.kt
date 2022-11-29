package com.example.petcareapp.Adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.DataClasses.DataClassVacPet
import com.example.petcareapp.PetsScreens.VaccinesPet
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VaccineAdapter(private val VaccinesArray:ArrayList<DataClassVacPet>, private val emailUser:String, private val idUser:String): RecyclerView.Adapter<VaccinesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccinesViewHolder {

        val view  = LayoutInflater.from(parent.context)

        return VaccinesViewHolder(view.inflate(R.layout.item_vaccine, parent, false))

    }

    override fun onBindViewHolder(holder: VaccinesViewHolder, position: Int) {
        val item = VaccinesArray.get(position)
        holder.renderVac(item)

        val binding = holder.binding
        binding.btnDeleteVac.setOnClickListener {


                val petsRepo = PetsRepository()

                val vacDeleted = petsRepo.deleteVac(item.id_vac.toInt())

                if(vacDeleted == "true"){
                    val bundle = Bundle()
                    bundle.putString("idUser", idUser)
                    bundle.putString("emailUser", emailUser )
                    bundle.putString("id", item.id_pet)
                    bundle.putString("name_pet", item.name_pet)
                    bundle.putString("race_pet", item.race_pet)

                    val editText = it.context as AppCompatActivity
                    val transaction = editText.supportFragmentManager.beginTransaction()
                    val fragmentVaccines = VaccinesPet()
                    fragmentVaccines.arguments = bundle
                    transaction.replace(R.id.frameLayout_pets , fragmentVaccines)
                    transaction.commit()
                    Toast.makeText(holder.itemView.context, "vaccine deleted!!" , Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(holder.itemView.context, "Error deleting vaccine" , Toast.LENGTH_SHORT).show()

                }



        }


    }

    override fun getItemCount(): Int {
    return VaccinesArray.size
    }
}