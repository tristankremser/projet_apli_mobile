package com.ismin.projet_apli_mobile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {
/*
        private lateinit var restaurant: Restaurant

        private var isFavori = false


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_restaurant_details)


            val btnRetour: Button = findViewById(R.id.seconde_activity_btnRetour)

                btnRetour.setOnClickListener {
                    returnToMainActivity()
                }

                val btnFavori: ImageButton = findViewById(R.id.btnFavori)
                btnFavori.setOnClickListener { toggleFavori() }
            }
        }

        private fun toggleFavori() {
            isFavori = !isFavori
            updateFavoriButton()
        }

        private fun updateFavoriButton() {
            val btnFavori: ImageButton = findViewById(R.id.btnFavori)

            if (isFavori) {
                btnFavori.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                btnFavori.setImageResource(android.R.drawable.btn_star_big_off)
            }
        }

        private fun displayRestaurantDetails(restaurant: Restaurant) {
            val nomoffreTextView = findViewById<TextView>(R.id.seconde_activity_nomoffre)
            val typeTextView = findViewById<TextView>(R.id.seconde_activity_type)
            val categorieTextView = findViewById<TextView>(R.id.seconde_activity_categorie)
            val adresseTextView = findViewById<TextView>(R.id.seconde_activity_adresse)
            val commtelTextView = findViewById<TextView>(R.id.seconde_activity_commtel)
            val commwebTextView = findViewById<TextView>(R.id.seconde_activity_commweb)
            val codepostalTextView = findViewById<TextView>(R.id.seconde_activity_codepostal)
            val communeTextView = findViewById<TextView>(R.id.seconde_activity_commune)
            val latitudeTextView = findViewById<TextView>(R.id.seconde_activity_latitude)
            val longitudeTextView = findViewById<TextView>(R.id.seconde_activity_longitude)
            val commmailTextView = findViewById<TextView>(R.id.seconde_activity_commmail)
            val videosurlTextView = findViewById<TextView>(R.id.seconde_activity_videosurl)
            val plateformeurlTextView = findViewById<TextView>(R.id.seconde_activity_plateformeurl)
            val languesParleesTextView = findViewById<TextView>(R.id.seconde_activity_languesparleesacceuil)
            val acceuilGroupeTextView = findViewById<TextView>(R.id.seconde_activity_acceuilgroupe)
            val tarifsTextView = findViewById<TextView>(R.id.seconde_activity_tarifs)
            val modePaiementTextView = findViewById<TextView>(R.id.seconde_activity_modepaiement)

            nomoffreTextView.text = "Nom: ${restaurant.nomoffre}"
            typeTextView.text = "Type: ${restaurant.type ?: "Non spécifié"}"
            categorieTextView.text = "Catégorie: ${restaurant.categorie ?: "Non spécifiée"}"
            adresseTextView.text = "Adresse: ${restaurant.adressecomplete ?: "Non spécifiée"}"
            commtelTextView.text = "Téléphone: ${restaurant.commtel ?: "Non spécifié"}"
            commwebTextView.text = "Site Web: ${restaurant.commweb ?: "Non spécifié"}"
            codepostalTextView.text = "Code postal: ${restaurant.codepostal ?: "Non spécifié"}"
            communeTextView.text = "Commune: ${restaurant.commune ?: "Non spécifiée"}"
            latitudeTextView.text = "Latitude: ${restaurant.latitude ?: "Non spécifiée"}"
            longitudeTextView.text = "Longitude: ${restaurant.longitude ?: "Non spécifiée"}"
            commmailTextView.text = "E-mail: ${restaurant.commmail ?: "Non spécifié"}"
            videosurlTextView.text = "URL des vidéos: ${restaurant.videosurl ?: "Non spécifiée"}"
            plateformeurlTextView.text = "Plateforme URL: ${restaurant.plateformeurl ?: "Non spécifiée"}"
            languesParleesTextView.text = "Langues parlées à l'accueil: ${restaurant.languesparleesacceuil?.joinToString(", ") ?: "Non spécifiées"}"
            acceuilGroupeTextView.text = "Accueil groupe: ${restaurant.acceuilgroupe ?: "Non spécifié"}"
            tarifsTextView.text = "Tarifs: ${restaurant.tarifs ?: "Non spécifiés"}"
            modePaiementTextView.text = "Modes de paiement: ${restaurant.modepaiement?.joinToString(", ") ?: "Non spécifiés"}"

        }

        private fun returnToMainActivity() {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EXTRA_RESTAURANT_NOM", restaurant.nomoffre)
            intent.putExtra("EXTRA_RESTAURANT_ISFAVORI", isFavori)
            startActivity(intent)


            finish()

        }


 */
}
