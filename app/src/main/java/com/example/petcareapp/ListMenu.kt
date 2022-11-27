package com.example.petcareapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.PetsScreens.PetsMenuActivity
import com.example.petcareapp.ProfileScreens.ProfileActivity
import com.example.petcareapp.Requests.RequestUserActivity
import com.example.petcareapp.Requests.RequestVetActivity
import com.example.petcareapp.VetPackage.ProfileVetActivity
import com.example.petcareapp.databinding.FragmentListMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class ListMenu : Fragment() {

    private var _binding: FragmentListMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailUser:String
    private lateinit var idlUser:String
    private lateinit var typeOfUser:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListMenuBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null){
            val email = requireArguments().getString("email")
            val id = requireArguments().getString("id")
            val type = requireArguments().getString("typeOfUser")
            binding.emailUser.text = email
            emailUser = email.toString()
            idlUser = id.toString()
            typeOfUser = type.toString()
        }


        if(typeOfUser == "user"){
            binding.emailUser.text = " Usuario: $emailUser "
        }else if(typeOfUser == "vet"){
            binding.emailUser.text = " Veterinario: $emailUser "
        }

        binding.containerProfile.setOnClickListener {

            if(typeOfUser == "user"){
                val intent = Intent(activity, ProfileActivity::class.java)
                intent.putExtra("email",emailUser )
                intent.putExtra("id",idlUser)
                intent.putExtra("typeOfUser","user")
                startActivity(intent)

            }

            if(typeOfUser == "vet"){

                val intent = Intent(activity, ProfileVetActivity::class.java)
                intent.putExtra("email",emailUser )
                intent.putExtra("id",idlUser)
                intent.putExtra("typeOfUser","vet")
                startActivity(intent)

                Toast.makeText(activity, "Vet profile", Toast.LENGTH_SHORT).show()
            }

        }
        binding.containerPets.setOnClickListener{

            if(typeOfUser == "user"){
                val intent = Intent(activity, PetsMenuActivity::class.java)
                intent.putExtra("email",emailUser )
                intent.putExtra("id",idlUser)
                startActivity(intent)
            }

            if(typeOfUser == "vet"){

                Toast.makeText(activity, "Vet profile", Toast.LENGTH_SHORT).show()
            }

        }

        binding.requestsId.setOnClickListener {
            if(typeOfUser == "user"){

                val intent = Intent(activity, RequestUserActivity::class.java)
                startActivity(intent)



            }
            if(typeOfUser == "vet"){
                                            // change  RequestVetActivity
                val intent = Intent(activity,RequestVetActivity::class.java)
                startActivity(intent)

            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*
        binding.containerProfile.setOnClickListener {
            Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show()

            val goToProfile = ProfileFragment()
            goToProfile.arguments
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout,goToProfile )?.commit()

        }


         */
        binding.btnShowData.setOnClickListener {

            Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show()

            val goToinfo = infoFragment()

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout,goToinfo )?.commit()

        }

    }







}