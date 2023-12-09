package com.ismin.projet_apli_mobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantAdapter(private var restaurants: List<Restaurant>, private var favorisList: List<String>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RestaurantViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_restaurant, parent, false)
        return RestaurantViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        var listFavoris: MutableList<String> = ArrayList(favorisList)
        holder.txvNomoffre.text = restaurant.nomoffre
        holder.txvCommune.text = restaurant.commune
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(restaurant)
        }
        if(listFavoris.contains(restaurant.nomoffre)) {
            holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_on)
        }else{
            holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_off)
        }

        holder.btnFavori.setOnClickListener {
            itemClickListener.onFavoriClick(restaurant)
            if (listFavoris.contains(restaurant.nomoffre)) {
                holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_off)
                listFavoris.remove(restaurant.nomoffre)
            } else {
                holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_on)
                listFavoris.add(restaurant.nomoffre)
            }
        }
        Picasso.get().load(restaurant.imageUrl).resize(48,48).centerCrop().into(holder.imgRestaurant)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    fun updateRestaurants(allRestaurants: List<Restaurant>) {
        restaurants = allRestaurants
        notifyDataSetChanged()
    }

}