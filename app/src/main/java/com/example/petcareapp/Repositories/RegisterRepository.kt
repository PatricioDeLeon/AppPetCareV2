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

    suspend fun register(name: String, email: String, password: String, phone: String): String {
        try {
//            val objeto = hashMapOf(
//                "name" to name,
//                "email" to email,
//                "password" to password,
//                "phone" to phone
//            )

            // Log.i("objeto", objeto.toString())

            val jsonObject = JSONObject()

            jsonObject.put("name", name)
            jsonObject.put("email", email)
            jsonObject.put("password", password)
            jsonObject.put("phone", phone)
            // Log.i("json ", jsonObject.toString())
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)

            //  val json = Gson().toJson(objeto)
            //  val URL  = "http://192.168.100.11/api/add_user_verify"
            val URL = "https://pet-care-service.onrender.com/api/add_user_verify"
            if (URL.isNotEmpty()) {
                // create http client
                // create http client
                val dataFromUrl = OkHttpClient()
                //build the request
//                val formBody = FormBody.Builder()
//                    .add("data", jsonObject)
//                    .build()
                val request = Request.Builder()
                    .url(URL)
                    .post(body)
                    .build()

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                //Enqueque the requ3est and handle the call backs
                return dataFromUrl.newCall(request).execute().body!!.string()

//                dataFromUrl.newCall(request).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        Log.i("FALOOOOO", request.toString())
//                        e.printStackTrace()
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        Log.i("Response", "Recived responses from server");
//                        response.use {
//                            if (response.isSuccessful) {
//                                result = response.body!!.string()
//                            } else {
//                                Log.e("http error", "server fail")
//                                result = "false"
//
//                            }
//                        }
//                    }
//                })


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
        phone: String
    ): String {


        try {
//            val objeto = hashMapOf(
//                "name_" to name,
//                "email" to email,
//                "password" to password,
//                "phone" to phone
//            )

            // Log.i("objeto", objeto.toString())

            val jsonObject = JSONObject()

            jsonObject.put("name_vet", name)
            jsonObject.put("email_vet", email)
            jsonObject.put("password_vet", password)
            jsonObject.put("cedula_vet", cedula)
            jsonObject.put("phone_vet", phone)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)

            //  val json = Gson().toJson(objeto)
            //  val URL  = "http://192.168.100.11/api/vets/add_vets_verify"
            val URL = "https://pet-care-service.onrender.com/api/vets/add_vets_verify"
            if (URL.isNotEmpty()) {
                // create http client
                // create http client
                val dataFromUrl = OkHttpClient()
                //build the request
//                val formBody = FormBody.Builder()
//                    .add("data", jsonObject)
//                    .build()
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