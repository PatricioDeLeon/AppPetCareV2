package com.example.petcareapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petcareapp.databinding.ActivityMenuBinding
import org.json.JSONArray
import org.json.JSONObject


class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            binding.emailUser.text = "Bienvenido/a $email -> #$id"

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

            binding.emailUser.text = "Bienvenido/a $email -> #$id"

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





    }





}


