package com.example.petcareapp.Requests

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.RequestsRepository
import com.example.petcareapp.databinding.ActivityGetCodeVetBinding
import com.example.petcareapp.databinding.ActivityRegisterVetBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetCodeVetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetCodeVetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetCodeVetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.statusRequest.visibility = View.GONE
        binding.sendAction.setOnClickListener {
            if(!binding.emailVet.text.toString().isNullOrEmpty()){

                CoroutineScope(Dispatchers.IO).launch {

                    val reqRepo = RequestsRepository()
                    val result = reqRepo.checkStatusCode(binding.emailVet.text.toString())

                    if(result == "false"){
                        runOnUiThread {
                            binding.statusRequest.visibility = View.VISIBLE
                            Toast.makeText(applicationContext, "Status...", Toast.LENGTH_SHORT ).show()
                            binding.statusRequest.text = "Peticion aun en revision"
                        }
                    }else if(result == "noExist"){
                        runOnUiThread {
                            binding.statusRequest.visibility = View.VISIBLE
                            binding.statusRequest.text = "No existe peticion con este email"
                        }
                    }else{

                        runOnUiThread {
                            binding.statusRequest.visibility = View.VISIBLE
                            binding.statusRequest.text = result
                        }

                    }
                }



            }
        }

        binding.sendARequests.setOnClickListener {

            val builder = Dialog(this)
            builder.setContentView(R.layout.dialog_send_req_code)
            val email_vet = builder.findViewById<EditText>(R.id.email_vet_req)
            val message_req = builder.findViewById<EditText>(R.id.message_req)
            val send = builder.findViewById<Button>(R.id.btnAcceptRequestCode)
            val closeModal = builder.findViewById<ImageButton>(R.id.close_modal_send_code)

            closeModal.setOnClickListener { builder.dismiss() }

            send.setOnClickListener {
                if(!email_vet.text.toString().isNullOrEmpty() && !message_req.text.toString().isNullOrEmpty()){

                    CoroutineScope(Dispatchers.IO).launch {

                        val reqRepo = RequestsRepository()
                        val res = reqRepo.sendRequestCode(email_vet.text.toString(), message_req.text.toString())

                        if(res == "exist"){
                            runOnUiThread {
                                Toast.makeText(applicationContext, " Peticion con este email ya en uso", Toast.LENGTH_SHORT ).show()
                            }
                        }else if(res == "false"){
                            runOnUiThread {
                                Toast.makeText(applicationContext, "Peticion fallada", Toast.LENGTH_SHORT ).show()
                            }
                        }else{
                            runOnUiThread {
                                Toast.makeText(applicationContext, "Peticion enviada correctamente", Toast.LENGTH_SHORT ).show()
                                builder.dismiss()
                            }
                        }
                    }
                }
            }
            builder.show()
        }

    }
}