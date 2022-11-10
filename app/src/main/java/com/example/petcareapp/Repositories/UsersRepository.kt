package com.example.petcareapp.Repositories

import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class UsersRepository {
    val prefix = "https://pet-care-service.onrender.com"
    fun getUserById(id:Int):String{
          // lateinit var result:String
        try{

            val URL  = "$prefix/api/get_user_by_id/$id"
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

//        Thread.sleep(500)
//        return result
    }

    fun updateUser(name:String, email:String, phone:String, id:String):String{
        //  lateinit var result2:String
        try{

            val objeto = hashMapOf(
                "name" to name,
                "email" to email,
                "phone" to phone,
                "id" to id.toInt()
            )
            val json = Gson().toJson(objeto)


            val URL  = "$prefix/api/update_user"
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
//                                result2 =  response.body!!.string()
//
//                            }else{
//                                Log.e("http error", "server fail")
//                                result2 = "false"
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
//        return result2
    }
}