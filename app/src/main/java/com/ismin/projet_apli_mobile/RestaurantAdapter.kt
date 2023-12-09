package com.ismin.projet_apli_mobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantAdapter(
    private var restaurants: List<Restaurant>,
    private var favorisList: List<String>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RestaurantViewHolder>() {

    // La méthode appelée lorsqu'une nouvelle vue (ViewHolder) doit être créée
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        // Inflé la vue de ligne (row) depuis le fichier XML "row_restaurant"
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_restaurant, parent, false)
        return RestaurantViewHolder(rowView)
    }

    // La méthode appelée pour remplir les données d'une vue (ViewHolder) avec les données d'un restaurant
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        // Récupère le restaurant à la position spécifiée dans la liste
        val restaurant = restaurants[position]

        // Copie de la liste des favoris pour pouvoir la modifier
        var listFavoris: MutableList<String> = ArrayList(favorisList)

        // Remplit les éléments de la vue avec les données du restaurant
        holder.txvNomoffre.text = restaurant.nomoffre
        holder.txvCommune.text = restaurant.commune

        // Gestion du clic sur la vue, envoie le restaurant à l'interface OnItemClickListener
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(restaurant)
        }

        // Vérifie si le restaurant est dans la liste des favoris et met à jour l'icône en conséquence
        if (listFavoris.contains(restaurant.nomoffre)) {
            holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_off)
        }

        // Gestion du clic sur l'icône de favori, envoie le restaurant à l'interface OnItemClickListener
        holder.btnFavori.setOnClickListener {
            itemClickListener.onFavoriClick(restaurant)

            // Met à jour l'icône de favori et la liste des favoris
            if (listFavoris.contains(restaurant.nomoffre)) {
                holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_off)
                listFavoris.remove(restaurant.nomoffre)
            } else {
                holder.btnFavori.setImageResource(android.R.drawable.btn_star_big_on)
                listFavoris.add(restaurant.nomoffre)
            }
        }

        // Charge l'image du restaurant depuis l'URL à l'aide de Picasso
        Picasso.get().load(restaurant.imageUrl).resize(48, 48).centerCrop().into(holder.imgRestaurant)
    }

    // La méthode qui retourne le nombre total d'éléments dans la liste
    override fun getItemCount(): Int {
        return restaurants.size
    }

    // Fonction pour mettre à jour la liste des restaurants dans l'adaptateur
    fun updateRestaurants(allRestaurants: List<Restaurant>) {
        restaurants = allRestaurants
        notifyDataSetChanged()
    }
}