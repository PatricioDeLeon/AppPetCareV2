package com.example.petcareapp.PetsScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import com.example.petcareapp.databinding.FragmentAddVaccineBinding
import com.example.petcareapp.databinding.FragmentPetsMenuFragmntBinding
import org.json.JSONArray


class AddVaccine : Fragment() {

    private var _binding: FragmentAddVaccineBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailUser:String
    private lateinit var idUser:String
    private lateinit var idPet:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val id_user = requireArguments().getString("id_user")
            val id_pet = requireArguments().getString("id_pet")
            val email_user = requireArguments().getString("email_user")
            val name_pet = requireArguments().getString("name_pet")
            val race_pet = requireArguments().getString("race_pet")
            idPet = id_pet.toString()
            idUser = id_user.toString()
            emailUser = email_user.toString()
            binding.namePet.setText(name_pet)
            binding.racePet.setText(race_pet)
        }

        binding.btnAddVac.setOnClickListener {
            // get values

            val vaccineVac = binding.vaccineVac.text.toString()
            val messageVac = binding.messageVac.text.toString()
            val namePet = binding.namePet.text.toString()
            val racePet = binding.racePet.text.toString()

            if (
                !namePet.isNotEmpty()
                || !racePet.isNotEmpty()
                || !vaccineVac.isNotEmpty()
                || messageVac.isNotEmpty()

            ){
                val petRepo = PetsRepository()

                val resultVac = petRepo.addVaccineById(idUser.toInt(),idPet.toInt(),namePet,racePet,vaccineVac,messageVac)

                if(resultVac == "false"){
                    Toast.makeText(activity, "Error al guardar la vacuna", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(activity, "Vacuna registrada", Toast.LENGTH_SHORT).show()

                    val bundle = Bundle()
                    bundle.putString("emailUser",emailUser )
                    bundle.putString("idUser", idUser)
                    bundle.putString("id", idPet)
                    val goToVaccineMenuPet = VaccinesPet()
                    goToVaccineMenuPet.arguments =  bundle
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.frameLayout_pets,goToVaccineMenuPet )?.commit()

                }

            }

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddVaccineBinding.inflate(inflater,container, false)
        return binding.root
    }


}