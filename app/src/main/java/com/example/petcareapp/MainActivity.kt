package com.example.petcareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.petcareapp.Repositories.LoginAuthRepository
import com.example.petcareapp.VetPackage.RegisterVet
import com.example.petcareapp.databinding.ActivityMainBinding
import com.example.petcareapp.databinding.ActivityMenuBinding
import com.example.petcareapp.databinding.LoginActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



            binding.goToMenu.setOnClickListener {

                if(!binding.vetCheck.isChecked){

                    val password = binding.passwordLogin.text.toString()
                    val email = binding.emailLogin.text.toString()
                    val chckBoxChecked = true

                    if(!password.isNullOrEmpty() && !email.isNullOrEmpty() ){

                        CoroutineScope(Dispatchers.IO).launch {
                            val loginRepo = LoginAuthRepository()
                            val res = loginRepo.login(email, password)

                            if(res == "false"){
                                runOnUiThread{
                                    Toast.makeText(applicationContext, "Cuenta no existe", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                runOnUiThread{
                                    Toast.makeText(applicationContext, res, Toast.LENGTH_SHORT).show()
                                    val intent = Intent(applicationContext, MenuActivity::class.java)
                                    intent.putExtra("userData", res)
                                    intent.putExtra("typeUser", chckBoxChecked.toString())
                                    startActivity(intent)
                                }
                            }
                        }

                    }else{
                        Toast.makeText(this, "Somethign is empty", Toast.LENGTH_SHORT).show()
                    }

                }else if(binding.vetCheck.isChecked){

                    val password = binding.passwordLogin.text.toString()
                    val email = binding.emailLogin.text.toString()
                    val chckBoxChecked = false

                    if(!password.isNullOrEmpty() && !email.isNullOrEmpty() ){
                        CoroutineScope(Dispatchers.IO).launch {

                            val loginRepo = LoginAuthRepository()
                            val res = loginRepo.loginVet(email, password)

                            if(res == "false"){
                                runOnUiThread{
                                    Toast.makeText(applicationContext, "Cuenta no existe", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                runOnUiThread{
                                    Toast.makeText(applicationContext, res, Toast.LENGTH_SHORT).show()
                                    val intent = Intent(applicationContext, MenuActivity::class.java)
                                    intent.putExtra("userData", res)
                                    intent.putExtra("typeUser", chckBoxChecked.toString())
                                    startActivity(intent)
                                }
                            }


                        }



                    }else{

                    }

                }

            }








        binding.goToRegister.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }

        binding.goToRegisterVet.setOnClickListener {

            val intent = Intent(this, RegisterVet::class.java)
            startActivity(intent)

        }


    }






}