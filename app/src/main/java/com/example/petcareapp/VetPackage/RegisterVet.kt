package com.example.petcareapp.VetPackage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.petcareapp.MainActivity
import com.example.petcareapp.RegisterActivity
import com.example.petcareapp.Repositories.RegisterRepository
import com.example.petcareapp.Requests.GetCodeVetActivity
import com.example.petcareapp.databinding.ActivityRegisterBinding
import com.example.petcareapp.databinding.ActivityRegisterVetBinding
import com.example.petcareapp.databinding.LoginActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterVet : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterVetBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterVetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        realTime = FirebaseDatabase.getInstance()


        binding.btnRegisterVet.setOnClickListener {

            val code = binding.codeVet.text.toString()
            val name = binding.nameRegister.text.toString()
            val email = binding.emailRegister.text.toString()
            val password = binding.passwordRegister.text.toString()
            val cedula = binding.cedulaRegister.text.toString()
            val phone = binding.phoneRegister.text.toString()

            if(!name.isNullOrEmpty() || !email.isNullOrEmpty() || !password.isNullOrEmpty() || !cedula.isNotEmpty() || !phone.isNullOrEmpty() || code.isNotEmpty()){


                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

                    val vet = hashMapOf(
                        "name_vet" to name,
                        "email_vet" to email,
                        "phone_vet" to phone,
                        "cedula_vet" to cedula
                    )

                    fireStore.collection("veterinarios").document(it.user?.uid.toString()).set(vet)

                    CoroutineScope(Dispatchers.IO).launch {

                        val registerRepo = RegisterRepository()
                        val res = registerRepo.registerVet(name,email,password,cedula, phone, code, it.user?.uid.toString())

                        if(res == "true"){
                            runOnUiThread{
                                Toast.makeText(applicationContext, "Account vet regitred in server and firebase", Toast.LENGTH_SHORT).show()
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }else if(res == "BadCode"){
                            runOnUiThread{
                                Toast.makeText(applicationContext, "codigo incorrecto", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            runOnUiThread{
                                Toast.makeText(applicationContext, "email o cedula ya existen en la db", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }.addOnFailureListener {
                    Toast.makeText(applicationContext,it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext, "complete los campos", Toast.LENGTH_SHORT).show()
            }

        }

        binding.goToMakeRequest.setOnClickListener {

            val intent = Intent(this, GetCodeVetActivity::class.java)
            startActivity(intent)

        }


        binding.btnLogin.setOnClickListener {
            val intent =
                Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }


    }
}