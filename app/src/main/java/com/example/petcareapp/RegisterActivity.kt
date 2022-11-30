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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.lang.Compiler.command





class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        realTime = FirebaseDatabase.getInstance()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val name = binding.nameRegister.text.toString()
            val email = binding.emailRegister.text.toString()
            val password = binding.passwordRegister.text.toString()
            val phone = binding.phoneRegister.text.toString()

            if(binding.passwordRegister.text.toString().length > 6) {


                if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty() && !phone.isNullOrEmpty()) {


                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {

                            val user = hashMapOf(
                                "name" to name,
                                "email" to email,
                                "phone" to phone
                            )
                            fireStore.collection("usuarios").document(it.user?.uid.toString())
                                .set(user)

                            //   realTime.reference.child("chats").child().child(it.user?.uid.toString())

                            CoroutineScope(Dispatchers.IO).launch {
                                val registerRepo = RegisterRepository()
                                val res = registerRepo.register(
                                    name,
                                    email,
                                    password,
                                    phone,
                                    it.user?.uid.toString()
                                )
                                if (res == "false") {
                                    runOnUiThread {
                                        Toast.makeText(
                                            applicationContext,
                                            "Cuenta de email ya existe",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    runOnUiThread {
                                        Toast.makeText(
                                            applicationContext,
                                            "Registro exitoso",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent =
                                            Intent(applicationContext, MainActivity::class.java)
                                        startActivity(intent)

                                    }
                                }
                            }

                        }.addOnFailureListener {
                        Log.i("Error: ", it.message.toString())
                    }
                } else {
                    Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(applicationContext, "Ingresa contraseña con mas de seis caracteres", Toast.LENGTH_SHORT).show()
            }


        }

        binding.btnLogin.setOnClickListener {
            val intent =
            Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

    }

    public fun showToast(text:String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()

    }

}