package com.example.petcareapp.Repositories

import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class LoginAuthRepository {

    val prefix = "https://pet-care-service.onrender.com"
    val prefixDev = "http://192.168.100.11"
    suspend fun login(email:String , password:String):String{
        try{
            val data = hashMapOf(
                "email" to email,
                "password" to password
            )
            val json = Gson().toJson(data)

            Log.i("json: ", json)

             val URL = "$prefix/api/auth_login"
            if(URL.isNotEmpty()){
                // create http client
                val dataFromUrl = OkHttpClient()
                //build the request
                val formBody = FormBody.Builder()
                    .add("data", json)
                    .build()

                val request = Request.Builder()
                    .url(URL)
                    .post(formBody)
                    .build()

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                //Enqueque the requ3est and handle the call backs

                return dataFromUrl.newCall(request).execute().body!!.string()


            }else{
                return "No existe el url"
            }

        }catch(e : Exception){
            return "NON"
        }
    }

    suspend fun loginVet(email:String , password:String):String{

        try{
            val data = hashMapOf(
                "email" to email,
                "password" to password
            )
            val json = Gson().toJson(data)
            Log.i("json: ", json)

            val jsonObject = JSONObject()

            jsonObject.put("password_vet",password )
            jsonObject.put("email_vet", email)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)
            val URL  = "$prefix/api/vets/auth_login_vet"
            if(URL.isNotEmpty()){
                val dataFromUrl = OkHttpClient()
                val request = Request.Builder()
                    .url(URL)
                    .post(body)
                    .build()

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                //Enqueque the requ3est and handle the call backs
                return dataFromUrl.newCall(request).execute().body!!.string()


            }else{
                return "No existe el url"
            }

        }catch(e : Exception){
            return "NON"
        }

    }

}