package com.example.petcareapp.PetsScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.VaccineAdapter
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.DataClasses.DataClassVacPet
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import com.example.petcareapp.databinding.FragmentVaccinesVetBinding
import org.json.JSONArray


class VaccinesPet : Fragment() {

    private var _binding: FragmentVaccinesVetBinding? = null
    private val binding get() = _binding!!
    val VaccinesList = ArrayList<DataClassVacPet>()
    private lateinit var emailUser:String
    private lateinit var idUser:String
    private lateinit var idPet:String
    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val id_user = requireArguments().getString("idUser")
            val email_user = requireArguments().getString("emailUser")
            val id_pet = requireArguments().getString("id")
//            val name_pet = requireArguments().getString("name_pet")
//            val race_pet = requireArguments().getString("race_pet")

            emailUser = email_user.toString()
            idUser =  id_user.toString()
            idPet = id_pet.toString()
        }

        val petsRepo = PetsRepository()
        val result = petsRepo.getVaccineByPetId(idPet.toInt())

        if(result == "false"){
            Toast.makeText(activity, "No hay pets para mostrar", Toast.LENGTH_SHORT).show()
        }else{

            val jsonArr = JSONArray(result)

            (0 until jsonArr.length()).forEach {
                val objectData =  jsonArr.getJSONObject(it)
                val dataPetVac = DataClassVacPet(
                    objectData.get("id_vac").toString(),
                    objectData.get("id_user").toString(),
                    objectData.get("id_pet").toString(),
                    objectData.get("name_pet").toString(),
                    objectData.get("race_pet").toString(),
                    objectData.get("vaccine_vac").toString(),
                    objectData.get("message_vac").toString(),
                    objectData.get("date_vac").toString()
                )
                VaccinesList.add(dataPetVac)
            }
        }
        initRecyclerView(VaccinesList)
        binding.btnRegisterVacPet.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("id_user", idUser)
            bundle.putString("id_pet", idPet)
            bundle.putString("email_user", emailUser)
            val goToAddVac = AddVaccine()
            goToAddVac.arguments =  bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout_pets,goToAddVac )?.commit()

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVaccinesVetBinding.inflate(inflater,container, false)
        return binding.root
    }

    fun initRecyclerView(VaccinesList:ArrayList<DataClassVacPet>){
        binding.recyclerVaccines.layoutManager = LinearLayoutManager(activity)
        binding.recyclerVaccines.adapter = VaccineAdapter(VaccinesList,emailUser, idUser)
    }


}