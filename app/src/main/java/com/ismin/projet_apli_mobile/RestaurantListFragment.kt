package com.ismin.projet_apli_mobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_RESTAURANTS = "param1"
private const val ARG_FAVORIS = "param2"

class RestaurantListFragment : Fragment(), OnItemClickListener {

    // Liste des restaurants affichés dans le fragment
    private var restaurants: ArrayList<Restaurant> = arrayListOf()

    // Liste des restaurants favoris
    private var favorisList: ArrayList<String> = arrayListOf()

    // Adaptateur pour la liste de restaurants
    private lateinit var restaurantAdapter: RestaurantAdapter

    // RecyclerView pour afficher la liste de restaurants
    private lateinit var recyclerView: RecyclerView

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Récupère les arguments passés au fragment (listes de restaurants et de favoris)
        arguments?.let {
            restaurants = it.getSerializable(ARG_RESTAURANTS) as ArrayList<Restaurant>
            favorisList = it.getSerializable(ARG_FAVORIS) as ArrayList<String>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflé la vue du fragment à partir du fichier XML "fragment_restaurant_list"
        val view = inflater.inflate(R.layout.fragment_restaurant_list, container, false)

        // Initialise le RecyclerView et l'Adaptateur
        recyclerView = view.findViewById(R.id.f_restaurant_list_rcv)
        restaurantAdapter = RestaurantAdapter(restaurants, favorisList, this)

        // Configure le RecyclerView avec un gestionnaire de disposition linéaire et une décoration de séparation
        recyclerView.adapter = restaurantAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        return view
    }

    // Fonction appelée lorsqu'un élément de la liste est cliqué
    override fun onItemClick(restaurant: Restaurant) {
        // Appelle la fonction de l'activité principale pour afficher les détails d'un restaurant
        (activity as MainActivity).displayRestaurantDetails(restaurant)
    }

    override fun onFavoriClick(restaurant: Restaurant){
        // Appelle la fonction de l'activité principale pour changer la listFavoris en conséquence
        (activity as MainActivity).changeFavoris(restaurant.nomoffre)
    }

    companion object {
        @JvmStatic
        fun newInstance(restaurants: ArrayList<Restaurant>,favorisList: ArrayList<String>) =
            RestaurantListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_RESTAURANTS, restaurants)
                    putSerializable(ARG_FAVORIS, favorisList)
                }
            }
    }
}
