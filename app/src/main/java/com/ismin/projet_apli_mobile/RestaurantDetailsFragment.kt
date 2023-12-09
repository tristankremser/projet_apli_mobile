package com.ismin.projet_apli_mobile


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_RESTAURANT = "param1"

class RestaurantDetailsFragment() : Fragment() {

    private lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restaurant = it.getSerializable(ARG_RESTAURANT) as Restaurant

        }
    }

    private lateinit var nomoffreTextView: TextView
    private lateinit var typeTextView: TextView
    private lateinit var categorieTextView: TextView
    private lateinit var adresseTextView: TextView
    private lateinit var commtelTextView: TextView
    private lateinit var commwebTextView: TextView
    private lateinit var codepostalTextView: TextView
    private lateinit var communeTextView: TextView
    private lateinit var latitudeTextView: TextView
    private lateinit var longitudeTextView: TextView
    private lateinit var commmailTextView: TextView
    private lateinit var videosurlTextView: TextView
    private lateinit var plateformeurlTextView: TextView
    private lateinit var languesParleesTextView: TextView
    private lateinit var acceuilGroupeTextView: TextView
    private lateinit var tarifsTextView: TextView
    private lateinit var modePaiementTextView: TextView
    private lateinit var btnRetour: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_restaurant_details, container, false)

        nomoffreTextView = view.findViewById(R.id.f_restaurant_details_nomoffre)
        typeTextView = view.findViewById(R.id.f_restaurant_details_type)
        categorieTextView = view.findViewById(R.id.f_restaurant_details_categorie)
        adresseTextView = view.findViewById(R.id.f_restaurant_details_adresse)
        commtelTextView = view.findViewById(R.id.f_restaurant_details_commtel)
        commwebTextView = view.findViewById(R.id.f_restaurant_details_commweb)
        codepostalTextView = view.findViewById(R.id.f_restaurant_details_codepostal)
        communeTextView = view.findViewById(R.id.f_restaurant_details_commune)
        latitudeTextView = view.findViewById(R.id.f_restaurant_details_latitude)
        longitudeTextView = view.findViewById(R.id.f_restaurant_details_longitude)
        commmailTextView = view.findViewById(R.id.f_restaurant_details_commmail)
        videosurlTextView = view.findViewById(R.id.f_restaurant_details_videosurl)
        plateformeurlTextView = view.findViewById(R.id.f_restaurant_details_plateformeurl)
        languesParleesTextView = view.findViewById(R.id.f_restaurant_details_languesparleesacceuil)
        acceuilGroupeTextView = view.findViewById(R.id.f_restaurant_details_acceuilgroupe)
        tarifsTextView = view.findViewById(R.id.f_restaurant_details_tarifs)
        modePaiementTextView = view.findViewById(R.id.f_restaurant_details_modepaiement)
        btnRetour = view.findViewById(R.id.f_restaurant_details_btnRetour)

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnRetour.setOnClickListener{
            (activity as MainActivity).displayRestaurantListFragment()
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(restaurant: Restaurant) =
            RestaurantDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_RESTAURANT, restaurant)
                }
            }
    }

}