package com.example.petcareapp.ProfileScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.UsersRepository
import com.example.petcareapp.databinding.FragmentProfileBinding
import org.json.JSONArray


class ProfileFragment : Fragment() {

    private var _binding:FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var emailUser:String
    private lateinit var idUser:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null){
            val emailRecived = requireArguments().getString("email")
            val idRecived = requireArguments().getString("id")
            binding.emailProfile.text = " Data: $emailRecived -> $idRecived "
            emailUser = emailRecived.toString()
            idUser = idRecived.toString()
        }

        val userRepo = UsersRepository()
        val idToUse = idUser.toInt()
        val res = userRepo.getUserById(idToUse)
        ////////////////////////////////////////////////


        Toast.makeText(activity, res, Toast.LENGTH_SHORT).show()
        val jsonArr = JSONArray(res)
        val jsonObj = jsonArr.getJSONObject(0)
        val name = jsonObj.get("name")
        val email = jsonObj.get("email")
        val id = jsonObj.get("id")
        val phone = jsonObj.get("phone")
        binding.emailUserView.text = email.toString()
        binding.nameUserView.text = name.toString()
        binding.phoneUserView.text = phone.toString()

        binding.goToEditUser.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("email", email.toString() )
            bundle.putString("name", name.toString())
            bundle.putString("phone", phone.toString())
            bundle.putString("id", id.toString())
            val goToEditUser = EditUserFragment()
            goToEditUser.arguments =  bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout_profile,goToEditUser )?.commit()

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}