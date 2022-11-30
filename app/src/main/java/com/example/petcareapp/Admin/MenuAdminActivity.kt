package com.example.petcareapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petcareapp.MenuActivity
import com.example.petcareapp.PetsScreens.AddVaccine
import com.example.petcareapp.PetsScreens.PetsMenuFragmnt
import com.example.petcareapp.R

import com.example.petcareapp.databinding.ActivityMenuAdminBinding

class MenuAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val transaction = supportFragmentManager.beginTransaction()
        val goToList = ListRequestsAdmin()
        transaction.replace(R.id.frameLayout_list_requests_code , goToList)
        transaction.commit()

        binding.btnLogoutAdmin.setOnClickListener {
            val intent = Intent(applicationContext, AdminActivity::class.java)
            finish()
            startActivity(intent)
        }


    }
}