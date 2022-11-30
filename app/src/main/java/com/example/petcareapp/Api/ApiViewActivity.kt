package com.example.petcareapp.Api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petcareapp.R
import com.example.petcareapp.databinding.ActivityApiViewBinding


class ApiViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApiViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        val goToApi = ListDataApi()
        transaction.replace(R.id.frameLayout_api , goToApi)
        transaction.commit()
    }
}