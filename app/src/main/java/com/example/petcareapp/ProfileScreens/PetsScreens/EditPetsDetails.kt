package com.example.petcareapp.ProfileScreens.PetsScreens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.ProfileScreens.ProfileActivity
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import com.example.petcareapp.databinding.FragmentEditPetsDetailsBinding
import com.example.petcareapp.databinding.FragmentPetsMenuFragmntBinding
import org.json.JSONArray


class EditPetsDetails : Fragment() {

    private var _binding: FragmentEditPetsDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var emailUser:String
    private lateinit var idUser:String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val id_user = requireArguments().getString("idUser")
            val email_user = requireArguments().getString("emailUser")
            val id_pet = requireArguments().getString("id")
            val name_pet = requireArguments().getString("name_pet")
            val age_pet = requireArguments().getString("age_pet")
            val race_pet = requireArguments().getString("race_pet")
            val weight_pet = requireArguments().getString("weight_pet")
            val additional_pet = requireArguments().getString("additional_pet")
            emailUser = email_user.toString()
            idUser =  id_user.toString()

            binding.idPet.text = id_pet
            binding.namePet.text = name_pet
            binding.agePet.text = age_pet
            binding.racePet.text = race_pet
            binding.weightPet.text = weight_pet
            binding.additionalPet.text = additional_pet
        }

        binding.btnDeletePet.setOnClickListener {
            val petRepo = PetsRepository()
            val petDeleted = petRepo.deletePet(binding.idPet.text.toString().toInt())
            if(petDeleted == "true"){
                Toast.makeText(activity, "Pet deleted", Toast.LENGTH_SHORT).show()

                val bundle = Bundle()
                bundle.putString("email",emailUser )
                bundle.putString("id", idUser)
                val goToMenuFragPet = PetsMenuFragmnt()
                goToMenuFragPet.arguments =  bundle
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.frameLayout_pets,goToMenuFragPet )?.commit()
            }
        }

        binding.btnUpdatePet.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("emailUser",emailUser)
            bundle.putString("idUser", idUser)
            bundle.putString("id_pet", binding.idPet.text.toString())
            bundle.putString("name_pet", binding.namePet.text.toString())
            bundle.putString("age_pet", binding.agePet.text.toString())
            bundle.putString("race_pet", binding.racePet.text.toString())
            bundle.putString("weight_pet", binding.weightPet.text.toString())
            bundle.putString("additional_pet", binding.additionalPet.text.toString())

            val goToUpdatePet = UpdatePet()
            goToUpdatePet.arguments =  bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout_pets,goToUpdatePet )?.commit()



        }












    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPetsDetailsBinding.inflate(inflater,container, false)
        return binding.root
    }


}