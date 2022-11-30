package com.example.petcareapp.Adapters.AdapterRequestsCode

import android.app.Dialog
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.Adapters.VaccinesViewHolder
import com.example.petcareapp.Admin.ListRequestsAdmin
import com.example.petcareapp.DataClasses.DataClassRequest
import com.example.petcareapp.DataClasses.DataClassRequestCode
import com.example.petcareapp.PetsScreens.EditPetsDetails
import com.example.petcareapp.R
import com.example.petcareapp.Repositories.RequestsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequestCodeAdapter(private val requestsArray:ArrayList<DataClassRequestCode>):RecyclerView.Adapter<RequestCodeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestCodeViewHolder {
        val view  = LayoutInflater.from(parent.context)
        return RequestCodeViewHolder(view.inflate(R.layout.item_request_code, parent, false))
    }

    override fun onBindViewHolder(holder: RequestCodeViewHolder, position: Int) {
        val item = requestsArray.get(position)
        holder.renderRequestsCode(item)

        holder.itemView.setOnClickListener {

            val builder = Dialog(holder.itemView.context)
            builder.setContentView(R.layout.dialog_manage_code_vet)

            val closeDialog =  builder.findViewById<ImageButton>(R.id.closeDialogCodeReqVet)
            val acceptReq = builder.findViewById<ImageButton>(R.id.btnAcceptReqCodeVet)
            val deleteReq = builder.findViewById<ImageButton>(R.id.btnDeleteReqCodeVet)

            closeDialog.setOnClickListener { builder.dismiss() }

            acceptReq.setOnClickListener {
                    val reqRepo = RequestsRepository()
                    val res = reqRepo.manageRequestsCode(item.email_vet, 1)
                    Toast.makeText(holder.itemView.context, item.email_vet, Toast.LENGTH_SHORT  ).show()
                    if(res == "true"){

                        Toast.makeText(holder.itemView.context, "Se acepto y genero codigo", Toast.LENGTH_SHORT).show()
                        builder.dismiss()
                        // go to fragment again
                        val editText = holder.itemView.context as AppCompatActivity
                        val transaction = editText.supportFragmentManager.beginTransaction()
                        val fragmentListReq = ListRequestsAdmin()
                        transaction.replace(R.id.frameLayout_list_requests_code, fragmentListReq)
                        transaction.commit()
                    }else{
                        Toast.makeText(holder.itemView.context, "Error al aceptar", Toast.LENGTH_SHORT).show()

                    }
            }

            deleteReq.setOnClickListener {

                val reqRepo = RequestsRepository()
                val res = reqRepo.manageRequestsCode(item.email_vet, 0)

                if(res == "true"){

                    Toast.makeText(holder.itemView.context, "Se elimino correctamente la peticion", Toast.LENGTH_SHORT).show()
                    builder.dismiss()

                    val editText = holder.itemView.context  as AppCompatActivity
                    val transaction = editText.supportFragmentManager.beginTransaction()
                    val fragmentListReq = ListRequestsAdmin()
                    transaction.replace(R.id.frameLayout_list_requests_code, fragmentListReq)
                    transaction.commit()
                }else{
                    Toast.makeText(holder.itemView.context, "Error al eliminar la peticion", Toast.LENGTH_SHORT).show()

                }

            }



            builder.show()

        }
    }

    override fun getItemCount(): Int {
        return requestsArray.size

    }

}