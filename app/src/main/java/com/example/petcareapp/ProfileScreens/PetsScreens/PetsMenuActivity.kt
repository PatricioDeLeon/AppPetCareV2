package com.example.petcareapp.ProfileScreens.PetsScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.petcareapp.ProfileScreens.ProfileFragment
import com.example.petcareapp.R
import com.example.petcareapp.databinding.ActivityPetsMenuBinding


class PetsMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetsMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // data
        val email = getIntent().getExtras()?.getString("email")
        val id = getIntent().getExtras()?.getString("id")

        super.onCreate(savedInstanceState)

        // binding
        binding = ActivityPetsMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = Bundle()
        bundle.putString("email",email.toString() )
        bundle.putString("id", id.toString())

        val transaction = supportFragmentManager.beginTransaction()
        val goToMenuPets = PetsMenuFragmnt()
        goToMenuPets.arguments = bundle
        transaction.replace(R.id.frameLayout_pets , goToMenuPets)
        transaction.commit()

    }
}