package com.example.petcareapp.ProfileScreens.PetsScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import com.example.petcareapp.databinding.FragmentEditPetsDetailsBinding
import com.example.petcareapp.databinding.FragmentUpdatePetBinding


class UpdatePet : Fragment() {

    private var _binding: FragmentUpdatePetBinding? = null
    private val binding get() = _binding!!

    private lateinit var emailUser:String
    private lateinit var idUser:String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val id_user = requireArguments().getString("idUser")
            val email_user = requireArguments().getString("emailUser")
            val id_pet = requireArguments().getString("id_pet")
            val name_pet = requireArguments().getString("name_pet")
            val age_pet = requireArguments().getString("age_pet")
            val race_pet = requireArguments().getString("race_pet")
            val weight_pet = requireArguments().getString("weight_pet")
            val additional_pet = requireArguments().getString("additional_pet")
            emailUser = email_user.toString()
            idUser =  id_user.toString()

            binding.idPet.text = id_pet
            binding.namePet.setText(name_pet)
            binding.agePet.setText(age_pet)
            binding.racePet.setText(race_pet)
            binding.weightPet.setText(weight_pet)
            binding.additionalPet.setText(additional_pet)



        }

        binding.btnUpdatePet.setOnClickListener {
            val petsRepo = PetsRepository()
            val petUpdated = petsRepo.updatePet(
                binding.idPet.text.toString().toInt(),
                binding.namePet.text.toString(),
                binding.agePet.text.toString(),
                binding.racePet.text.toString(),
                binding.weightPet.text.toString(),
                binding.additionalPet.text.toString()
            )

            if(petUpdated == "true"){
                Toast.makeText(activity, "Pet Updated Successfuly", Toast.LENGTH_SHORT).show()

                val bundle = Bundle()
                bundle.putString("email",emailUser )
                bundle.putString("id", idUser)
                val goToMenuFragPet = PetsMenuFragmnt()
                goToMenuFragPet.arguments =  bundle
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.frameLayout_pets,goToMenuFragPet )?.commit()
            }
        }













    }







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdatePetBinding.inflate(inflater,container, false)
        return binding.root
    }


}