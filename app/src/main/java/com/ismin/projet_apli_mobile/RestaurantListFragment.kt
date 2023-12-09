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
    private var restaurants: ArrayList<Restaurant> = arrayListOf()
    private var favorisList: ArrayList<String> = arrayListOf()
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var recyclerView: RecyclerView

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restaurants = it.getSerializable(ARG_RESTAURANTS) as ArrayList<Restaurant>
            favorisList = it.getSerializable(ARG_FAVORIS) as ArrayList<String>
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_list, container, false)

        recyclerView = view.findViewById(R.id.f_restaurant_list_rcv)
        restaurantAdapter = RestaurantAdapter(restaurants,favorisList, this)
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



    override fun onItemClick(restaurant: Restaurant) {
        (activity as MainActivity).displayRestaurantDetails(restaurant)
    }

    override fun onFavoriClick(restaurant: Restaurant){
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
