package com.example.petcareapp.ProfileScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.UsersRepository
import com.example.petcareapp.databinding.FragmentEditUserBinding


class EditUserFragment : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var idUser:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val emailRecived = requireArguments().getString("email")
            val nameRecived = requireArguments().getString("name")
            val phoneRecived = requireArguments().getString("phone")
            val idRecived = requireArguments().getString("id")
            idUser = idRecived.toString()
            binding.editName.setText(nameRecived)
            binding.editEmail.setText(emailRecived)
            binding.editPhone.setText(phoneRecived)
        }

        binding.updateUser.setOnClickListener {
            if(!binding.editEmail.text.toString().isNullOrEmpty() || !binding.editName.text.toString().isNullOrEmpty() ||  !binding.editPhone.text.toString().isNullOrEmpty()){
                //data to send
                val email = binding.editEmail.text.toString()
                val name = binding.editName.text.toString()
                val phone = binding.editPhone.text.toString()
                val userRepo = UsersRepository()

                val result = userRepo.updateUser(name, email, phone,idUser)
                if(result == "true"){
                    Toast.makeText(activity, "Data Updated", Toast.LENGTH_SHORT).show()

                    val bundle = Bundle()
                    bundle.putString("id", idUser)
                    val goToProfile = ProfileFragment()
                    goToProfile.arguments =  bundle
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.frameLayout_profile,goToProfile )?.commit()
                }else{
                  Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()

               }


            }else{

                Toast.makeText(activity, "Complete los campos", Toast.LENGTH_SHORT).show()

            }
        }


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditUserBinding.inflate(inflater,container, false)
        return binding.root
    }


}