package com.ismin.projet_apli_mobile

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txvNomoffre: TextView = itemView.findViewById(R.id.r_restaurant_txv_nomoffre)
    val txvCommune: TextView = itemView.findViewById(R.id.r_restaurant_txv_commune)
    val btnFavori: ImageButton = itemView.findViewById(R.id.r_restaurant_btnFavori)
    val imgRestaurant: ImageView = itemView.findViewById(R.id.imgRestaurant)
}