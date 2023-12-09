package com.ismin.projet_apli_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), RestaurantCreator{

    private val restaurantList = RestaurantList()
    private val favorisList = mutableListOf<String>()

    private val floatingActionButton: FloatingActionButton by lazy {
        findViewById(R.id.a_main_btn_create_restaurant)
    }

    private lateinit var restaurantAdapter: RestaurantAdapter


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://restaurants.cleverapps.io/")
        .build()

    private val restaurantService = retrofit.create(RestaurantService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restaurantService.getAllRestaurants()
            .enqueue(object : Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    if (response.isSuccessful) {
                        val allRestaurants: List<Restaurant> = response.body()!!
                        restaurantList.addRestaurants(allRestaurants)
                        displayRestaurantListFragment()
                    }
                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    Log.e("Retrofit", "Error: ${t.message}", t)
                }
            })

        floatingActionButton.setOnClickListener {
            displayCreateRestaurantFragment()
        }


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_delete -> {
                    restaurantList.clear()
                    true
                }
                R.id.action_refresh -> {
                    displayRestaurantListFragment()
                    true
                }

                R.id.action_list -> {
                    true
                }
                R.id.action_map -> {
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_info -> {
                    val intent = Intent(this, InfoActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }

    fun changeFavoris(nom: String) {
        if (favorisList.contains(nom)) {
            favorisList.remove(nom)
        }
        else{
            favorisList.add(nom)
        }
    }

    fun displayRestaurantListFragment() {
        val sortedRestaurantList = restaurantList.getAllRestaurants().sortedWith { restaurant1, restaurant2 ->
            restaurant1.isFavoriFirstComparator(restaurant2)
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.a_main_lyt_fragment,
            RestaurantListFragment.newInstance(ArrayList(sortedRestaurantList), ArrayList(favorisList))
        )
        transaction.commit()
        floatingActionButton.visibility = View.VISIBLE

    }

    fun Restaurant.isFavoriFirstComparator(other: Restaurant): Int {
        return when {
            favorisList.contains(this.nomoffre) && !favorisList.contains(other.nomoffre) -> -1
            !favorisList.contains(this.nomoffre) && favorisList.contains(other.nomoffre) -> 1
            else -> 0
        }
    }

    fun displayRestaurantDetails(restaurant: Restaurant) {
        val detailsFragment = RestaurantDetailsFragment.newInstance(restaurant)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.a_main_lyt_fragment, detailsFragment)

        transaction.commit()
    }

    private fun displayCreateRestaurantFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.a_main_lyt_fragment,
            CreateRestaurantFragment()
        )
        transaction.commit()
        floatingActionButton.visibility = View.GONE
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onRestaurantCreated(restaurant: Restaurant) {
        restaurantService.addRestaurant(restaurant)
            .enqueue {
                onResponse = {
                    val restaurantFromServer: Restaurant? = it.body()
                    restaurantList.addRestaurant(restaurantFromServer!!)
                }

                onFailure = {
                    Toast.makeText(this@MainActivity, it?.message, Toast.LENGTH_SHORT).show()
                }
            }

        restaurantList.addRestaurant(restaurant)
        displayRestaurantListFragment()
    }



}
