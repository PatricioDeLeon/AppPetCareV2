package com.example.petcareapp.VetPackage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.petcareapp.MainActivity
import com.example.petcareapp.Repositories.RegisterRepository
import com.example.petcareapp.databinding.ActivityRegisterBinding
import com.example.petcareapp.databinding.ActivityRegisterVetBinding
import com.example.petcareapp.databinding.LoginActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterVet : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterVetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterVetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegisterVet.setOnClickListener {
            val code = binding.codeVet.text.toString()
            val name = binding.nameRegister.text.toString()
            val email = binding.emailRegister.text.toString()
            val password = binding.passwordRegister.text.toString()
            val cedula = binding.cedulaRegister.text.toString()
            val phone = binding.phoneRegister.text.toString()

            if(!code.isNotEmpty() || !name.isNullOrEmpty() || !email.isNullOrEmpty() || !password.isNullOrEmpty() || !cedula.isNotEmpty() || !phone.isNullOrEmpty()){



                CoroutineScope(Dispatchers.IO).launch {
                    val registerRepo = RegisterRepository()
                    val res = registerRepo.registerVet(name,email,password,cedula, phone)

                    if(res == "true"){
                        runOnUiThread{

                            Toast.makeText(applicationContext, "Account regitred!!!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)

                        }


                    }else{
                        runOnUiThread{
                            Toast.makeText(applicationContext, "email o cedula ya existen en la db", Toast.LENGTH_SHORT).show()

                        }


                    }

                }



            }else{
                Toast.makeText(applicationContext, "complete los campos", Toast.LENGTH_SHORT).show()
            }

        }





    }
}