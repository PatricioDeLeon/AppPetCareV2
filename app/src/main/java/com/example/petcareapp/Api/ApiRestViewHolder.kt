package com.example.petcareapp.Api

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.petcareapp.R
import com.example.petcareapp.databinding.ItemImageApiBinding
import com.squareup.picasso.Picasso

class ApiRestViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemImageApiBinding.bind(view)

    fun renderImage(image:String){
        Picasso.get().load(image).into(binding.imageBucket)
    }

}