package com.example.petcareapp.Repositories

import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class RegisterRepository {
    private lateinit var resultVetRegistred: String

    val prefix = "https://pet-care-service.onrender.com"
    val prefixDev = "http://192.168.100.11"

    suspend fun register(name: String, email: String, password: String, phone: String): String {
        try {

            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("email", email)
            jsonObject.put("password", password)
            jsonObject.put("phone", phone)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)
            val URL = "$prefix/api/add_user_verify"
            if (URL.isNotEmpty()) {
                val dataFromUrl = OkHttpClient()

                val request = Request.Builder()
                    .url(URL)
                    .post(body)
                    .build()

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                //Enqueque the requ3est and handle the call backs
                return dataFromUrl.newCall(request).execute().body!!.string()


            } else {
                return "No existe el url"
            }

        } catch (e: Exception) {
            return "NON"
        }

    }

    suspend fun registerVet(
        name: String,
        email: String,
        password: String,
        cedula: String,
        phone: String,
        code:String,
    ): String {


        try {

            val jsonObject = JSONObject()
            jsonObject.put("code", code)
            jsonObject.put("name_vet", name)
            jsonObject.put("email_vet", email)
            jsonObject.put("password_vet", password)
            jsonObject.put("cedula_vet", cedula)
            jsonObject.put("phone_vet", phone)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)

            val URL = "$prefix/api/vets/add_vets_verify"
            if (URL.isNotEmpty()) {

                val dataFromUrl = OkHttpClient()
                val request = Request.Builder()
                    .url(URL)
                    .post(body)
                    .build()

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                //Enqueque the requ3est and handle the call backs
                return dataFromUrl.newCall(request).execute().body!!.string()

            } else {
                return "No existe el url"
            }

        } catch (e: Exception) {
            return "NON"
        }

    }
}