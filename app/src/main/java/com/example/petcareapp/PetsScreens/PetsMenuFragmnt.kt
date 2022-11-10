package com.example.petcareapp.PetsScreens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.PetsAdapter
import com.example.petcareapp.DataClasses.DataClassPets
import com.example.petcareapp.ProfileScreens.EditUserFragment
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import com.example.petcareapp.databinding.FragmentListMenuBinding
import com.example.petcareapp.databinding.FragmentPetsMenuFragmntBinding
import org.json.JSONArray
import org.json.JSONObject


class PetsMenuFragmnt : Fragment() {

    private var _binding: FragmentPetsMenuFragmntBinding? = null
    private val binding get() = _binding!!
    val petsList = ArrayList<DataClassPets>()
    private lateinit var emailUser:String
    private lateinit var idUser:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val emailRecived = requireArguments().getString("email")
            val idRecived = requireArguments().getString("id")
            emailUser = emailRecived.toString()
            idUser = idRecived.toString()
        }

        binding.btnRegisterPet.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("email",emailUser )
            bundle.putString("id", idUser)
            val goToAddPets = addPet()
            goToAddPets.arguments =  bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout_pets,goToAddPets )?.commit()

        }

        val petsRepo = PetsRepository()
        val result = petsRepo.getPetsById(idUser.toInt())

        if(result == "false"){
            Toast.makeText(activity, "No hay pets para mostrar", Toast.LENGTH_SHORT).show()
        }else{
            val jsonArr = JSONArray(result)
            Log.i("DATA jsonArr ", jsonArr.toString())
            // val jsonObj = jsonArr.getJSONObject(0)
            (0 until jsonArr.length()).forEach {
                val objectData =  jsonArr.getJSONObject(it)
                val dataToAdd =  DataClassPets(
                    objectData.get("id").toString(),
                    objectData.get("name_pet").toString(),
                    objectData.get("age_pet").toString(),
                    objectData.get("race_pet").toString(),
                    objectData.get("weight_pet").toString(),
                    objectData.get("additional_pet").toString()
                )
                petsList.add(dataToAdd)
            }
            initRecyclerView(petsList)
            //  Toast.makeText(activity, jsonArr.toString(), Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPetsMenuFragmntBinding.inflate(inflater,container, false)
        return binding.root
    }

    private fun initRecyclerView(petsList:ArrayList<DataClassPets>){
        binding.recyclerPets.layoutManager = LinearLayoutManager(activity)
        binding.recyclerPets.adapter = PetsAdapter(petsList,emailUser, idUser)
    }




}