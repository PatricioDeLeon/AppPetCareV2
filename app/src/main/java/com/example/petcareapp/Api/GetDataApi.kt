package com.example.petcareapp.Api

import android.os.StrictMode
import okhttp3.OkHttpClient
import okhttp3.Request

class GetDataApi {

    fun getDataApi(nameVet:String):String{

        try{
            val URL  = "https://dog.ceo/api/breed/$nameVet/images"

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
}