package com.example.petcareapp.Repositories

import android.os.StrictMode
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class VetRepositoy {

    val prefix = "https://pet-care-service.onrender.com"
    val prefixDev = "http://192.168.100.11"
    fun getVetById(id:Int):String{
        try{

            val URL  = "$prefixDev/api/vets/get_vet_by_id/$id"
            if(URL.isNotEmpty()){
                // create http client
                val dataFromUrl = OkHttpClient()
                //build the request

                val request = Request.Builder()
                    .url(URL)
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

    fun updateVet(name:String, email:String, phone:String, id:String):String{
        try{

            val jsonObject = JSONObject()
            jsonObject.put("id_vet", id.toInt())
            jsonObject.put("name_vet", name)
            jsonObject.put("email_vet", email)
            jsonObject.put("phone_vet", phone)


            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)

            val URL  = "$prefixDev/api/vets/update_vet"
            if(URL.isNotEmpty()){
                // create http client
                val dataFromUrl = OkHttpClient()
                //build the request


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