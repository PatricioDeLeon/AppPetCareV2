package com.example.petcareapp.VetPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.ProfileScreens.EditUserFragment
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.UsersRepository
import com.example.petcareapp.Repositories.VetRepositoy
import com.example.petcareapp.databinding.FragmentProfileBinding
import com.example.petcareapp.databinding.FragmentProfileVetBinding
import org.json.JSONArray


class ProfileVetFragment : Fragment() {

    private var _binding: FragmentProfileVetBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailUser:String
    private lateinit var idUser:String
    private lateinit var typeUser:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null){
            val emailRecived = requireArguments().getString("email")
            val idRecived = requireArguments().getString("id")
            val typeOfUser = requireArguments().getString("typeOfUser")
            emailUser = emailRecived.toString()
            idUser = idRecived.toString()
            typeUser = typeOfUser.toString()
        }

        val vetRepo = VetRepositoy()
        val res = vetRepo.getVetById(idUser.toInt())
        Toast.makeText(activity, res, Toast.LENGTH_SHORT).show()
        val jsonArr = JSONArray(res)
        val jsonObj = jsonArr.getJSONObject(0)
        val name = jsonObj.get("name_vet")
        val email = jsonObj.get("email_vet")
        val id = jsonObj.get("id_vet")
        val phone = jsonObj.get("phone_vet")
        val cedula = jsonObj.get("cedula_vet")

        binding.nameVetView.text = name.toString()
        binding.emailVetView.text = email.toString()
        binding.cedulaVetView.text = cedula.toString()
        binding.phoneVetView.text = phone.toString()
        binding.idVetView.text = id.toString()

        binding.goToEditVet.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("email", email.toString() )
            bundle.putString("name", name.toString())
            bundle.putString("phone", phone.toString())
            bundle.putString("id", id.toString())
            val goToEditVet = EditVetFragment()
            goToEditVet.arguments =  bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout_profile_vet,goToEditVet )?.commit()

        }

    }










    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileVetBinding.inflate(inflater,container, false)
        return binding.root
    }


}