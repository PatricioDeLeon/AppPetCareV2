package com.example.petcareapp.Repositories

import android.os.StrictMode
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RequestsRepository {

    val prefix = "https://pet-care-service.onrender.com"
    val prefixDev = "http://192.168.100.11"
    fun sendRequestCode(email:String, message:String):String{

        val jsonObject = JSONObject()
        jsonObject.put("email_vet", email)
        jsonObject.put("message_req", message)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonObject.toString().toRequestBody(mediaType)

        try{
            val URL  = "$prefixDev/api/vets/add_req_vet"
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

    fun checkStatusCode(email:String):String{

        val jsonObject = JSONObject()
        jsonObject.put("email_vet", email)

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonObject.toString().toRequestBody(mediaType)

        try{
            val URL  = "$prefixDev/api/vets/ckeck_status_code_vet"
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

    fun getAllRequests():String{

        try{
            val URL  = "$prefixDev/api/vets/get_all_requests"
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
            return "Error data requests code"
        }

    }

}