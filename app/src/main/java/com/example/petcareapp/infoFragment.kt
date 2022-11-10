package com.example.petcareapp

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petcareapp.ProfileScreens.ProfileFragment
import com.example.petcareapp.databinding.FragmentInfoBinding
import okhttp3.*
import java.io.IOException


class infoFragment : Fragment() {
    private  var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding  = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {
            Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show()
            val newFragment = ProfileFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout,newFragment )?.commit()
        }

        // onCLickListener for url button
    binding.urlButton.setOnClickListener {
        val URL  = "http://192.168.100.11/api/get_all_users"
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
            dataFromUrl.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.i("Response", "Recived responses from server");
                    response.use {
                        if(response.isSuccessful){
                            binding.data.text = response.body!!.string()
                        }else{
                            Log.e("http error", "server fail")
                        }
                    }
                }
            })



        }else{
            Toast.makeText(activity, "URL was empty", Toast.LENGTH_SHORT).show()
        }
    }

    }


}