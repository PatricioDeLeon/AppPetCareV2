package com.example.petcareapp.Admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.AdapterRequestsCode.RequestCodeAdapter
import com.example.petcareapp.Adapters.RequestsOfUsersToVetAdapter
import com.example.petcareapp.DataClasses.DataClassRequestCode
import com.example.petcareapp.DataClasses.DataClassVacPet
import com.example.petcareapp.PetsScreens.AddVaccine
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.PetsRepository
import com.example.petcareapp.Repositories.RequestsRepository
import com.example.petcareapp.databinding.FragmentListRequestsAdminBinding
import com.example.petcareapp.databinding.FragmentVaccinesVetBinding
import org.json.JSONArray

class ListRequestsAdmin : Fragment() {

    private var _binding: FragmentListRequestsAdminBinding? = null
    private val binding get() = _binding!!
    val requests = ArrayList<DataClassRequestCode>()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val reqRepo = RequestsRepository()
        val res = reqRepo.getAllRequests()

        if(res != "noExist"){

            val jsonArr = JSONArray(res)

            Log.i("Data", jsonArr.toString())

            (0 until jsonArr.length()).forEach {
                val objectData =  jsonArr.getJSONObject(it)
                val data = DataClassRequestCode(
                    objectData.get("email_vet").toString(),
                    objectData.get("message_req").toString()
                )
                requests.add(data)

            }

            initRecyclerView(requests)

        }else{
            Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
        }




    }

    fun initRecyclerView(data:ArrayList<DataClassRequestCode>){

        binding.recyclerRequestsCodes.layoutManager = LinearLayoutManager(activity)
        binding.recyclerRequestsCodes.adapter = RequestCodeAdapter(data)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListRequestsAdminBinding.inflate(inflater,container, false)
        return binding.root
    }




}