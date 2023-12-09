package com.ismin.projet_apli_mobile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

class CreateRestaurantFragment : Fragment() {

    // Interface pour permettre la communication avec l'activité hôte
    private lateinit var listener: RestaurantCreator

    // Composants de l'interface utilisateur
    private lateinit var edtNomoffre: EditText
    private lateinit var edtType: EditText
    private lateinit var edtCategorie: EditText
    private lateinit var edtAdresse: EditText
    private lateinit var edtCommtel: EditText
    private lateinit var edtCommweb: EditText
    private lateinit var edtLatitude: EditText
    private lateinit var edtLongitude: EditText
    private lateinit var btnSave: Button

    // Méthode appelée lors de la création de la vue du fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflater le layout du fragment
        val view = inflater.inflate(R.layout.fragment_create_restaurant, container, false)

        // Initialisation des composants de l'interface à partir du layout
        edtNomoffre = view.findViewById(R.id.f_create_restaurant_edt_nomoffre)
        edtType = view.findViewById(R.id.f_create_restaurant_edt_type)
        edtCategorie = view.findViewById(R.id.f_create_restaurant_edt_categorie)
        edtAdresse = view.findViewById(R.id.f_create_restaurant_edt_adresse)
        edtCommtel = view.findViewById(R.id.f_create_restaurant_edt_commtel)
        edtCommweb = view.findViewById(R.id.f_create_restaurant_edt_commweb)
        edtLatitude = view.findViewById(R.id.f_create_restaurant_edt_latitude)
        edtLongitude = view.findViewById(R.id.f_create_restaurant_edt_longitude)

        btnSave = view.findViewById(R.id.f_create_restaurant_btn_save)
        btnSave.setOnClickListener {
            // Récupération des valeurs saisies par l'utilisateur
            val nomoffre = edtNomoffre.text.toString()
            val type = edtType.text.toString()
            val categorie = edtCategorie.text.toString()
            val adresse = edtAdresse.text.toString()
            val commtel = edtCommtel.text.toString()
            val commweb = edtCommweb.text.toString()
            val latitude = edtLatitude.text.toString().toDouble()
            val longitude = edtLongitude.text.toString().toDouble()

            // Création d'un objet Restaurant avec les données saisies
            val restaurant = Restaurant(
                nomoffre, type, categorie, "", "", "", 0, "", latitude, longitude,
                commtel, "", commweb, "", "", null, "", "", null, adresse
            )

            // Appel de la méthode de l'interface pour informer l'activité hôte
            listener.onRestaurantCreated(restaurant)
        }

        return view
    }

    // Méthode appelée lors de l'attachement du fragment à l'activité
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RestaurantCreator) {
            listener = context
        } else {
            throw IllegalStateException("$context must implement RestaurantCreator")
        }
    }
}
