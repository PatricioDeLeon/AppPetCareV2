package com.example.petcareapp.Api

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcareapp.Adapters.PetsAdapter
import com.example.petcareapp.R
import com.example.petcareapp.databinding.FragmentListDataApiBinding
import com.example.petcareapp.databinding.FragmentProfileBinding
import org.json.JSONArray
import org.json.JSONObject


class ListDataApi : Fragment() {

    private var _binding: FragmentListDataApiBinding? = null
    private val binding get() = _binding!!
    val image = ArrayList<String>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.searchPet.setOnClickListener {
            Toast.makeText(activity, "Cargando Imagenes... ", Toast.LENGTH_SHORT).show()
            if(!binding.typePet.text.toString().isNullOrEmpty()){
                image.clear()
                val repoApi = GetDataApi()
                val response = repoApi.getDataApi(binding.typePet.text.toString().trim())


                if(response != null){
                    val json = JSONObject(response)
                    val arrayJson = json.get("message")
                    val arrjson = JSONArray(arrayJson.toString())
                    (0 until arrjson.length()).forEach {

                        val objectData = arrjson.get(it)
                        image.add(objectData.toString())
                    }
                    initRecyclerView(image)

                }

            }else{
                Toast.makeText(activity, "Ingrese datos", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListDataApiBinding.inflate(inflater,container, false)
        return binding.root
    }

    fun initRecyclerView(data:ArrayList<String>){

        binding.recyclerApi.layoutManager = LinearLayoutManager(activity)
        binding.recyclerApi.adapter = ApiRestAdapter(data)

    }

}