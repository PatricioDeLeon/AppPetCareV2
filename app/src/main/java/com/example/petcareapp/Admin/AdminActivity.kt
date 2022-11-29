package com.example.petcareapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petcareapp.RegisterActivity
import com.example.petcareapp.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnterAdmin.setOnClickListener {

            if(!binding.emailAdmin.text.toString().isNullOrEmpty() && !binding.passwordAdmin.text.toString().isNullOrEmpty()){

                if(binding.emailAdmin.text.toString() == "admin@gmail.com" && binding.passwordAdmin.text.toString() == "admin123"){
                    val intent = Intent(this, MenuAdminActivity::class.java)
                    startActivity(intent)
                }
            }

        }


    }
}