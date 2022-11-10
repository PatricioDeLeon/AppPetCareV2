package com.example.petcareapp.Adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.PetsScreens.EditPetsDetails
import com.example.petcareapp.R

class PetsAdapter(private val petsArray:ArrayList<DataClassPets>, private val emailUser:String, private val idUser:String): RecyclerView.Adapter<PetsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        val view  = LayoutInflater.from(parent.context)

        return PetsViewHolder(view.inflate(R.layout.item_pet, parent, false))
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        val item = petsArray.get(position)
        holder.render(item)
         // click en cualquier card


        holder.itemView.setOnClickListener {

            val sendData = petsArray.get(position)

//            val intent = Intent(holder.itemView.context, EditPetActivity::class.java)
//            intent.putExtra("idUser",idUser )
//            intent.putExtra("emailUser",emailUser )
//            intent.putExtra("id",sendData.id )
//            intent.putExtra("name_pet",sendData.name_pet)
//            intent.putExtra("age_pet",sendData.age_pet)
//            intent.putExtra("race_pet",sendData.race_pet)
//            intent.putExtra("weight_pet",sendData.weight_pet)
//            intent.putExtra("additional_pet",sendData.additional_pet)
//            holder.itemView.context.startActivity(intent)

            val bundle = Bundle()

            bundle.putString("idUser", idUser)
            bundle.putString("emailUser", emailUser )

            bundle.putString("id", sendData.id )
            bundle.putString("name_pet", sendData.name_pet)
            bundle.putString("age_pet", sendData.age_pet)
            bundle.putString("race_pet", sendData.race_pet)
            bundle.putString("weight_pet", sendData.weight_pet)
            bundle.putString("additional_pet", sendData.additional_pet)
            val editText = it.context as AppCompatActivity
            val transaction = editText.supportFragmentManager.beginTransaction()
            val fragmentEditPet = EditPetsDetails()
            fragmentEditPet.arguments = bundle
            transaction.replace(R.id.frameLayout_pets , fragmentEditPet)
            transaction.commit()



        }


//    deletePetAction.setOnClickListener {
//        val PetsMenuFrag = PetsMenuFragmnt()
//        val sendData = petsArray.get(position)
//         // Toast.makeText(holder.itemView.context, "Accedio a eliminar" , Toast.LENGTH_SHORT).show()
//        val petRepo = PetsRepository()
//        val petDeleted = petRepo.deletePet(sendData.id.toInt())
//        if(petDeleted == "true"){
//            Toast.makeText(holder.itemView.context, "Pet Deleted" , Toast.LENGTH_SHORT).show()
//
//        }
//
//    }


    }

    override fun getItemCount(): Int {

        return petsArray.size

    }


}