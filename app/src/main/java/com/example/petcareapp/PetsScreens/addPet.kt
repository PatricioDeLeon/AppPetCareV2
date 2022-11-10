package com.example.petcareapp.PetsScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.ProfileScreens.ProfileFragment
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import com.example.petcareapp.databinding.FragmentAddPetBinding



class addPet : Fragment() {

    private var _binding: FragmentAddPetBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailUser:String
    private lateinit var idUser:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val emailUserRecived = requireArguments().getString("email")
            val idUserRecived = requireArguments().getString("id")
            emailUser = emailUserRecived.toString()
            idUser = idUserRecived.toString()
        }

        Toast.makeText(activity, "$emailUser $idUser", Toast.LENGTH_SHORT).show()

        binding.btnAddPet.setOnClickListener {

            if(!binding.namePet.text.toString().isNullOrEmpty() && !binding.agePet.text.toString().isNullOrEmpty() && !binding.racePet.text.toString().isNullOrEmpty() && !binding.weightPet.text.toString().isNullOrEmpty() && !binding.additonalPet.text.toString().isNullOrEmpty() ){
                Toast.makeText(activity, "Accedio a insertar", Toast.LENGTH_SHORT).show()
                // acceder a registrar mascota

                val petRepo = PetsRepository()

                val result = petRepo.registerPet(idUser.toInt(),binding.namePet.text.toString(), binding.agePet.text.toString(), binding.racePet.text.toString(), binding.weightPet.text.toString(),binding.additonalPet.text.toString() )

                if(result == "true"){
                    Toast.makeText(activity, "Mascota Registrada con exito", Toast.LENGTH_SHORT).show()

                    val bundle = Bundle()
                    bundle.putString("id", idUser)
                    bundle.putString("email", emailUser)
                    val goToMenuPets = PetsMenuFragmnt()
                    goToMenuPets.arguments =  bundle
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.frameLayout_pets,goToMenuPets )?.commit()

                }else{
                    Toast.makeText(activity, "Error en el registro", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(activity, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPetBinding.inflate(inflater,container, false)
        return binding.root

    }


}