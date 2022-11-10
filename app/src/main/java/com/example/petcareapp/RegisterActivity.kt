package com.example.petcareapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.Settings.Global
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petcareapp.Repositories.RegisterRepository
import com.example.petcareapp.databinding.ActivityRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.lang.Compiler.command





class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val name = binding.nameRegister.text.toString()
            val email = binding.emailRegister.text.toString()
            val password = binding.passwordRegister.text.toString()
            val phone = binding.phoneRegister.text.toString()

            if(!name.isNullOrEmpty() || !email.isNullOrEmpty() || !password.isNullOrEmpty() || !phone.isNullOrEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    val registerRepo = RegisterRepository()
                    val res =  registerRepo.register(name,email,password,phone)
                    if(res == "false"){
                        runOnUiThread{
                            Toast.makeText(applicationContext, "EMIAL ALREDY EXISR", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        runOnUiThread{
                            Toast.makeText(applicationContext, "Account regitred!!!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }else{
                Toast.makeText(applicationContext, "ERROR!!", Toast.LENGTH_SHORT).show()

            }


        }
    }

    public fun showToast(text:String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()

    }

}