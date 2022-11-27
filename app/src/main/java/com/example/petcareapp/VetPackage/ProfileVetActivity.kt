package com.example.petcareapp.VetPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.petcareapp.ProfileScreens.ProfileFragment
import com.example.petcareapp.R
import com.example.petcareapp.databinding.ActivityProfileBinding
import com.example.petcareapp.databinding.ActivityProfileVetBinding

class ProfileVetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileVetBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val email = getIntent().getExtras()?.getString("email")
        val id = getIntent().getExtras()?.getString("id")
        val typeOfUser = getIntent().getExtras()?.getString("typeOfUser")

        super.onCreate(savedInstanceState)
        binding = ActivityProfileVetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = Bundle()
        bundle.putString("email",email.toString() )
        bundle.putString("id", id.toString())
        bundle.putString("typeOfUser", typeOfUser.toString())

        val transaction = supportFragmentManager.beginTransaction()
        val fragmentMenu = ProfileVetFragment()
        fragmentMenu.arguments = bundle
        transaction.replace(R.id.frameLayout_profile_vet , fragmentMenu)
        //  transaction.addToBackStack(null)
        transaction.commit()

        Toast.makeText(applicationContext, "data: $email -> $id", Toast.LENGTH_SHORT).show()




    }
}