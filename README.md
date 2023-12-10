L'application est composée : 
-d'une MainActivity qui va permettre de récupérer la liste des restaurants via l'url et va ensuite afficher un des fragments de l'application.
-d'une MapActivity accessible via la toolbar qui récupere également la lsite des restaurants via l'url, nous avons fait le choix de ne pas envoyer cette liste via l'intent parce que nous rencontrions des problèmes de transaction, nous réutilisons ainsi retrofit pour obtenir la liste des restaurants et récupérer leurs coordonnées pour les afficher sur google map.
-d'une InfoActivity qui affiche simplement textView comprenant l'url de la base de données ainsi que des informations générales sur l'application.

Lorsque l'on ouvre l'application on arrive directement sur MainActivity qui va afficher le fragment RestaurantListFragment qui permet d'afficher la liste des restaurants(il est possible qu'au premier lancement de l'application la liste ne s'affiche pas mais en redémarrant cela devrait fonctionner). 
Il y a ensuite plusieurs fonctionnalités: 
- un bouton supprimer qui supprime l'entiereté de la liste
- un bouton actualiser qui permet d'actualiser la liste
- un bouton toolbar qui permet de naviguer entre chaque activité
- des boutons Favori en forme d'étoile sur chaque restaurant permettant de l'ajouter ou l'enlever de la liste des favoris, après actualisation la liste est rangée de telle sorte à ce que les restaurants favoris soient affichés en premier
- chaque restaurant est cliquable et un clic permet d'afficher les détails du restaurant en question à l'aide de RestaurantDetailsFragment
- un bouton ajouter un restaurant en bas à droite qui permet d'ajouter un restaurant à la base de données via une méthode POST

L'affichage des différents fragments est géré à l'aide de supportFragmentManager qui permet de réaliser des transactions pour changer l'instance de l'affichage de MainActivity.
Les clicks sont quant à eux gérés à l'aide d'une interface onItemClickedListener, qui gère donc à la fois les clicks pour afficher les détails d'un restaurant mais également ceux qui pour ajouter/enlever un restaurant aux favoris
