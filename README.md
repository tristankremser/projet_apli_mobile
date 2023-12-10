# Projet Apli Mobile - Gestion de Restaurants

Ce projet est une application Android conçue pour gérer un service de restaurants. Elle offre diverses fonctionnalités telles que l'affichage, l'ajout, la suppression, et la gestion des restaurants favoris.

## Fonctionnalités

### Affichage et Gestion des Restaurants
- *Afficher la Liste des Restaurants* : Récupère et affiche la liste des restaurants via une URL.
- *Afficher les Détails des Restaurants* : Permet d'afficher les détails d'un restaurant spécifique.
- *Afficher les Restaurants sur une Carte* : Utilise Google Maps pour afficher les restaurants sur une carte.
- *Ajouter un Restaurant* : Permet d'ajouter un nouveau restaurant à la base de données.
- *Supprimer les Restaurants* : Permet de supprimer tout les restaurants de la liste.
- *Gérer les Favoris* : Permet d'ajouter ou de retirer des restaurants de la liste des favoris.

### Architecture du Projet

#### Code Source Java/Kotlin
- *MainActivity.kt* : Point d'entrée principal de l'application, gérant l'affichage initial et la navigation entre les fragments.
- *MapActivity.kt* : Affiche les restaurants sur une carte Google Maps.
- *InfoActivity.kt* : Affiche des informations générales sur l'application.
- *CreateRestaurantFragment.kt* : Fragment pour créer et ajouter un nouveau restaurant.
- *RestaurantDetailsFragment.kt* : Fragment pour afficher les détails d'un restaurant.
- *RestaurantListFragment.kt* : Fragment pour afficher la liste des restaurants.

#### Ressources et Layouts
- *activity_main.xml* : Layout principal de l'application.
- *activity_map.xml* : Layout pour l'affichage de la carte Google Maps.
- *activity_info.xml* : Layout pour l'affichage des informations de l'application.
- *fragment_create_restaurant.xml* : Layout pour le fragment de création d'un restaurant.
- *fragment_restaurant_details.xml* : Layout pour le fragment d'affichage des détails d'un restaurant.
- *fragment_restaurant_list.xml* : Layout pour le fragment d'affichage de la liste des restaurants.
- *row_restaurant.xml* : Layout pour chaque élément de la liste des restaurants.
- *menu_main.xml* : Définit le menu de l'application.
- *Drawable et Mipmap* : Ressources graphiques comme les icônes.
- *Values* : Ressources telles que les chaînes de caractères, les couleurs, et les styles.

## Installation

Pour exécuter l'application, clonez le dépôt et ouvrez-le dans un environnement de développement Android comme Android Studio.
