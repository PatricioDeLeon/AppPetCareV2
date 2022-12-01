package com.example.petcareapp.VetPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.ProfileScreens.ProfileFragment
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.VetRepositoy
import com.example.petcareapp.databinding.FragmentEditUserBinding
import com.example.petcareapp.databinding.FragmentEditVetBinding
import com.example.petcareapp.databinding.FragmentProfileVetBinding


class EditVetFragment : Fragment() {

    private var _binding: FragmentEditVetBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailUser:String
    private lateinit var idUser:String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val emailRecived = requireArguments().getString("email")
            val nameRecived = requireArguments().getString("name")
            val phoneRecived = requireArguments().getString("phone")
            val idRecived = requireArguments().getString("id")
            idUser = idRecived.toString()
            binding.editNameVet.setText(nameRecived)
            binding.editEmailVet.setText(emailRecived)
            binding.editPhoneVet.setText(phoneRecived)
        }

        binding.updateVet.setOnClickListener {
            if(!binding.editNameVet.text.toString().isNullOrEmpty() && !binding.editEmailVet.text.toString().isNullOrEmpty() && !binding.editPhoneVet.text.toString().isNullOrEmpty()){
                val vetRepo = VetRepositoy()
                val result = vetRepo.updateVet(binding.editNameVet.text.toString(),binding.editEmailVet.text.toString(),binding.editPhoneVet.text.toString(),idUser)

                if(result == "true"){

                    Toast.makeText(activity, "Información actualizada", Toast.LENGTH_SHORT).show()
                    val bundle = Bundle()
                    bundle.putString("id", idUser)
                    val goToProfile = ProfileVetFragment()
                    goToProfile.arguments =  bundle
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.frameLayout_profile_vet,goToProfile )?.commit()

                }else{
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.goProfileVet.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("id", idUser)
            val goToProfile = ProfileVetFragment()
            goToProfile.arguments =  bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout_profile_vet,goToProfile )?.commit()

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditVetBinding.inflate(inflater,container, false)
        return binding.root
    }


}