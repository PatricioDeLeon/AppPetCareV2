package com.example.petcareapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petcareapp.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject


class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var realTime: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        fireStore = FirebaseFirestore.getInstance()
        realTime = FirebaseDatabase.getInstance()

        val userUid = firebaseAuth.currentUser?.uid


        val data = getIntent().getExtras()?.getString("userData")
        val typeOfUser = getIntent().getExtras()?.getString("typeUser")
        val jsonArr = JSONArray(data)
        val jsonObj = jsonArr.getJSONObject(0)

        if(typeOfUser == "true"){


            val name = jsonObj.get("name")
            val email = jsonObj.get("email")
            val id = jsonObj.get("id")
            Log.i("name", name.toString())

            binding = ActivityMenuBinding.inflate(layoutInflater)
            setContentView(binding.root)


            val bundle = Bundle()
            bundle.putString("email",email.toString() )
            bundle.putString("id", id.toString())
            bundle.putString("typeOfUser", "user")

            val transaction = supportFragmentManager.beginTransaction()
            val fragmentMenu = ListMenu()
            fragmentMenu.arguments = bundle
            transaction.replace(R.id.frameLayout , fragmentMenu)
            //  transaction.addToBackStack(null)
            transaction.commit()


        }else{
            // vet

            val name = jsonObj.get("name_vet")
            val email = jsonObj.get("email_vet")
            val id = jsonObj.get("id_vet")
            Log.i("name", name.toString())

            binding = ActivityMenuBinding.inflate(layoutInflater)
            setContentView(binding.root)


            val bundle = Bundle()
            bundle.putString("email",email.toString() )
            bundle.putString("id", id.toString())
            bundle.putString("typeOfUser", "vet")

            val transaction = supportFragmentManager.beginTransaction()
            val fragmentMenu = ListMenu()
            fragmentMenu.arguments = bundle
            transaction.replace(R.id.frameLayout , fragmentMenu)
            //  transaction.addToBackStack(null)
            transaction.commit()

        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(applicationContext,MainActivity::class.java)
            finish()
            startActivity(intent)

        }

    }





}


