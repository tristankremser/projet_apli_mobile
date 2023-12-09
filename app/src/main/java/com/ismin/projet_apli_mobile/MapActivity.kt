package com.ismin.projet_apli_mobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    // Liste des restaurants
    private val restaurantList = RestaurantList()

    // Retrofit pour les appels réseau
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://restaurants.cleverapps.io/")
                .build()

                    // Service pour effectuer des appels réseau liés aux restaurants
                    private val restaurantService = retrofit.create(RestaurantService::class.java)

                    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_map)

                // Récupération de la liste de tous les restaurants depuis le serveur
                restaurantService.getAllRestaurants()
                    .enqueue(object : Callback<List<Restaurant>> {
                        override fun onResponse(
                            call: Call<List<Restaurant>>,
                            response: Response<List<Restaurant>>
                        ) {
                            if (response.isSuccessful) {
                                // Ajout des restaurants à la liste locale
                                val allRestaurants: List<Restaurant> = response.body()!!
                                Log.d("Retrofit", "Réponse du serveur: ${response.raw().toString()}")
                                restaurantList.addRestaurants(allRestaurants)
                            } else {
                                Log.e("Retrofit", "Code de réponse: ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                            // Gestion des erreurs lors de l'appel réseau
                            Toast.makeText(this@MapActivity, t.message, Toast.LENGTH_SHORT).show()
                            Log.e("Retrofit", "Error: ${t.message}", t)
                        }
                    })

                // Instanciation de la carte Google
                val supportMapFragment: SupportMapFragment = SupportMapFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .add(R.id.map_container, supportMapFragment)
                    .commit()

                supportMapFragment.getMapAsync(this)

                // Configuration de la barre d'outils
                val toolbar: Toolbar = findViewById(R.id.toolbar)
                setSupportActionBar(toolbar)

                // Gestion des actions du menu
                toolbar.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_delete -> {
                            true
                        }
                        R.id.action_refresh -> {
                            true
                        }
                        R.id.action_list -> {
                            // Navigation vers l'activité principale
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            true
                        }
                        R.id.action_map -> {
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

    override fun onMapReady(googleMap: GoogleMap) {

        Log.d("Test", "$restaurantList")
        //ajout de chaque restaurant à la map
        restaurantList.getAllRestaurants().customForEach { restaurant ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(restaurant.latitude ?: 0.0, restaurant.longitude ?: 0.0))
                    .title(restaurant.nomoffre)
            )
        }
        val centrePaysDeLaLoire = LatLng(47.4667, -0.8333)

        // Animation de la caméra vers le centre du pays de la Loire
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centrePaysDeLaLoire, 7.0f))
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun <T> List<T>.customForEach(action: (T) -> Unit) {
        for (element in this) {
            action(element)
        }
    }
}
