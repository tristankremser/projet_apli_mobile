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

class MainActivity : AppCompatActivity(), RestaurantCreator {

    // Liste des restaurants dans l'application
    private val restaurantList = RestaurantList()

    // Liste des restaurants favoris
    private val favorisList = mutableListOf<String>()

    // Bouton flottant pour créer un nouveau restaurant
    private val floatingActionButton: FloatingActionButton by lazy {
        findViewById(R.id.a_main_btn_create_restaurant)
    }

    // Adapter pour la liste des restaurants
    private lateinit var restaurantAdapter: RestaurantAdapter

    // Initialisation de Retrofit pour les appels réseau
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://restaurants.cleverapps.io/")
        .build()

    // Service pour effectuer des appels réseau liés aux restaurants
    private val restaurantService = retrofit.create(RestaurantService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                        restaurantList.addRestaurants(allRestaurants)
                        // Affichage de la liste des restaurants dans un fragment
                        displayRestaurantListFragment()
                    }
                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    // Gestion des erreurs lors de l'appel réseau
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    Log.e("Retrofit", "Error: ${t.message}", t)
                }
            })

        // Gestion du clic sur le bouton flottant pour créer un nouveau restaurant
        floatingActionButton.setOnClickListener {
            displayCreateRestaurantFragment()
        }

        // Configuration de la barre d'outils
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Gestion des actions du menu
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_delete -> {
                    // Suppression de tous les restaurants de la liste locale
                    restaurantList.clear()
                    true
                }
                R.id.action_refresh -> {
                    // Rafraîchissement et réaffichage de la liste des restaurants
                    displayRestaurantListFragment()
                    true
                }
                R.id.action_map -> {
                    // Navigation vers l'activité de la carte
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_info -> {
                    // Navigation vers l'activité d'information
                    val intent = Intent(this, InfoActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }

    // Fonction pour changer l'état de favori d'un restaurant
    fun changeFavoris(nom: String) {
        if (favorisList.contains(nom)) {
            favorisList.remove(nom)
        } else {
            favorisList.add(nom)
        }
    }

    // Fonction pour afficher le fragment de liste des restaurants
    fun displayRestaurantListFragment() {
        // Tri de la liste des restaurants en fonction des favoris
        val sortedRestaurantList = restaurantList.getAllRestaurants().sortedWith { restaurant1, restaurant2 ->
            restaurant1.isFavoriFirstComparator(restaurant2)
        }
        // Remplacement du fragment actuel par le fragment de liste des restaurants
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.a_main_lyt_fragment,
            RestaurantListFragment.newInstance(ArrayList(sortedRestaurantList), ArrayList(favorisList))
        )
        transaction.commit()
        // Affichage du bouton flottant
        floatingActionButton.visibility = View.VISIBLE
    }

    // Comparateur pour trier les restaurants en fonction des favoris
    fun Restaurant.isFavoriFirstComparator(other: Restaurant): Int {
        return when {
            favorisList.contains(this.nomoffre) && !favorisList.contains(other.nomoffre) -> -1
            !favorisList.contains(this.nomoffre) && favorisList.contains(other.nomoffre) -> 1
            else -> 0
        }
    }




    fun displayRestaurantDetails(restaurant: Restaurant) {
        // Crée un nouveau fragment de détails du restaurant avec les détails du restaurant fourni
        val detailsFragment = RestaurantDetailsFragment.newInstance(restaurant)

        // Commence une transaction pour remplacer le fragment actuel par le fragment de détails
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.a_main_lyt_fragment, detailsFragment)

        // Applique les modifications de la transaction
        transaction.commit()
        floatingActionButton.visibility = View.GONE
    }



    private fun displayCreateRestaurantFragment() {
        // Commence une transaction pour remplacer le fragment actuel par le fragment de création
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.a_main_lyt_fragment,
            CreateRestaurantFragment()
        )

        // Applique les modifications de la transaction
        transaction.commit()

        // Cache le bouton flottant pendant l'affichage du fragment de création
        floatingActionButton.visibility = View.GONE
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate le menu à partir du fichier de ressources "menu_main"
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onRestaurantCreated(restaurant: Restaurant) {
        // Envoie une requête réseau pour ajouter le restaurant au serveur
        restaurantService.addRestaurant(restaurant)
            .enqueue {
                onResponse = {
                    // Ajoute le restaurant du serveur à la liste locale
                    val restaurantFromServer: Restaurant? = it.body()
                    restaurantList.addRestaurant(restaurantFromServer!!)
                }

                onFailure = {
                    // Gestion des erreurs lors de l'appel réseau pour ajouter un restaurant
                    Toast.makeText(this@MainActivity, it?.message, Toast.LENGTH_SHORT).show()
                }
            }

        // Ajoute le restaurant à la liste locale et réaffiche la liste des restaurants
        restaurantList.addRestaurant(restaurant)
        displayRestaurantListFragment()
    }


}
