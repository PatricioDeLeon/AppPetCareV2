package com.example.petcareapp.Repositories

import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class PetsRepository {

    val prefix = "https://pet-care-service.onrender.com"

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
//                dataFromUrl.newCall(request).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        e.printStackTrace()
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        Log.i("Response", "Recived responses from server");
//                        response.use {
//                            if(response.isSuccessful){
//
//                                result =  response.body!!.string()
//
//                            }else{
//                                Log.e("http error", "server fail")
//                                result = "false"
//
//                            }
//                        }
//                    }
//                })



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
//                dataFromUrl.newCall(request).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        e.printStackTrace()
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        Log.i("Response", "Recived responses from server");
//                        response.use {
//                            if(response.isSuccessful){
//                                resultPet =  response.body!!.string()
//                            }else{
//                                Log.e("http error", "server fail")
//                                resultPet = "false"
//
//                            }
//                        }
//                    }
//                })



            }else{
                return "No existe el url"
            }

        }catch(e : Exception){
            return "NON"
        }


    }

    fun deletePet (id:Int):String{
        //  lateinit var resultPetDeeted:String

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
//                dataFromUrl.newCall(request).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        e.printStackTrace()
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        Log.i("Response", "Recived responses from server");
//                        response.use {
//                            if(response.isSuccessful){
//
//                                resultPetDeeted =  response.body!!.string()
//
//                            }else{
//                                Log.e("http error", "server fail")
//                                resultPetDeeted = "false"
//
//                            }
//                        }
//                    }
//                })



            }else{
                return "No existe el url"
            }

        }catch(e : Exception){
            return "NON"
        }

//        Thread.sleep(500)
//        return resultPetDeeted
    }


    fun updatePet(idPet:Int,namePet:String,agePet:String, racePet:String, weightPet:String, additionalPet:String ):String{
        //  lateinit var resultPetUpdated:String

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
//                dataFromUrl.newCall(request).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        e.printStackTrace()
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        Log.i("Response", "Recived responses from server");
//                        response.use {
//                            if(response.isSuccessful){
//                                resultPetUpdated =  response.body!!.string()
//                            }else{
//                                Log.e("http error", "server fail")
//                                resultPetUpdated = "false"
//
//                            }
//                        }
//                    }
//                })



            }else{
                return "No existe el url"
            }

        }catch(e : Exception){
            return "NON"
        }

//
//        Thread.sleep(500)
//         return resultPetUpdated
    }
}