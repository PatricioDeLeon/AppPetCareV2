package com.example.petcareapp.ProfileScreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.petcareapp.ListMenu
import com.example.petcareapp.MenuActivity
import com.example.petcareapp.R
import com.example.petcareapp.databinding.ActivityMenuBinding
import com.example.petcareapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val email = getIntent().getExtras()?.getString("email")
        val id = getIntent().getExtras()?.getString("id")
        val typeOfUser = getIntent().getExtras()?.getString("typeOfUser")
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = Bundle()
        bundle.putString("email",email.toString() )
        bundle.putString("id", id.toString())
        bundle.putString("typeOfUser", typeOfUser.toString())

        val transaction = supportFragmentManager.beginTransaction()
        val fragmentMenu = ProfileFragment()
        fragmentMenu.arguments = bundle
        transaction.replace(R.id.frameLayout_profile , fragmentMenu)
        //  transaction.addToBackStack(null)
        transaction.commit()

         // Toast.makeText(applicationContext, "data: $email -> $id", Toast.LENGTH_SHORT).show()



    }
}