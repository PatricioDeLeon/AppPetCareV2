package com.example.petcareapp.Repositories

import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class PetsRepository {

    val prefix = "https://pet-care-service.onrender.com"
    val prefixDev = "http://192.168.100.11"
    fun getPetsById(id:Int):String{

        try{
            val URL  = "$prefix/api/get_pet_by_user/$id"
            if(URL.isNotEmpty()){
                // create http client
                val dataFromUrl = OkHttpClient()
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

    fun registerPet(id_user: Int, name:String, age:String, race:String, weight:String, additional:String):String{

        try{
            val objeto = hashMapOf(
                "id_user" to id_user,
                "name_pet" to name,
                "age_pet" to age,
                "race_pet" to race,
                "weight_pet" to weight,
                "additional_pet" to additional
            )
            val json = Gson().toJson(objeto)

            Log.i("Data to send ", json)

            val URL  = "$prefix/api/add_pet"

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

    fun deletePet (id:Int):String{

        try{

            val objeto = hashMapOf( "id" to id )
            val json = Gson().toJson(objeto)
            Log.i("Data to send ", json)
            val URL  = "$prefix/api/delete_pet/$id"

            if(URL.isNotEmpty()){
                // create http client
                val dataFromUrl = OkHttpClient()
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


    fun updatePet(idPet:Int,namePet:String,agePet:String, racePet:String, weightPet:String, additionalPet:String ):String{

        try{
            val objeto = hashMapOf(
                "id" to idPet,
                "name_pet" to namePet,
                "age_pet" to agePet,
                "race_pet" to racePet,
                "weight_pet" to weightPet,
                "additional_pet" to additionalPet
            )
            val json = Gson().toJson(objeto)

            Log.i("Data to send ", json)

            val URL  = "$prefix/api/update_pet"

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

    fun getVaccineByPetId(id:Int):String{
        try{
            val URL  = "$prefix/api/get_vaccine_by_pet/$id"

            if(URL.isNotEmpty()){
                // create http client
                val dataFromUrl = OkHttpClient()

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

    fun addVaccineById(idUser:Int, idPet:Int, namePet:String, racePet:String, vaccineVac:String, messageVac:String, dateVAC:String):String{

        val jsonObject = JSONObject()
        jsonObject.put("id_user", idUser)
        jsonObject.put("id_pet", idPet)
        jsonObject.put("name_pet", namePet)
        jsonObject.put("race_pet", racePet)
        jsonObject.put("vaccine_vac", vaccineVac)
        jsonObject.put("message_vac", messageVac)
        jsonObject.put("date_vac", dateVAC)

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonObject.toString().toRequestBody(mediaType)


        try{
            val URL  = "$prefix/api/add_vaccine_pet"

            if(URL.isNotEmpty()){
                // create http client
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