# ussd-api-master


Test Controller : 
----------> via init-app-db  : dépreciated
Account : ok
Role :  ok
User : ok 

----------> via main-app :

role : ok : initialiser au lancement avec data.sql

Account : ok via init controller -> creation admin user  

Account : ok mulitipe ( existing account) with UserControlleur   

user : ok   ajout des roleNames sous forme de liste : A faire

ServiceCode : ok

Coverage  : OK

UssdService : ok

mno : ok

routeMno : OK 

menu : ok 

menuOption : ok 

expiration jwt : 



Workflow 

1 - Auto creation d'un compte   : un compte peut avoir plusieur utilisateur ratachés
    Accompte Name - Email- MotDepasse 
    		date creation
    		date modification

  Cet email sera l'utilisation  admin du compte : done


2 - Création du Service USSD



3 - Menu et option flow  (MISS CI)

 - On crée le main menu  mainMenu =true ; menuType = 0 (1)
   NB : il sera judicieux de creer tous les menus ( ecran ) avant de poursuivre.

- On cree un menu pour chaque option du mainMenu ;  mainMenu = false ,  menuType = 0   (2)
   pour mon cas ( Miss CI) le menu crée affichera : * le nombre de votant de l'option selectionnée
                                                    * deux options continuer oui/non

- On creer un menu pour le cas option continuer oui et on affiche plusieur option ( envoyer message ; consulter stat );mainMenu = false , menuType = 0  (3)

- On creer le menu pour envoyer un message ; mainMenu = false ,  menuType = 1  (4)  

-On cree le menu de consulation de stat ;  mainMenu = false ,  menuType = 2  (5)  



 - On creer les Options  du menu (1)

 - On creer les Options  du menu (2)

 - On creer les Options  du menu (3)

 - (4) ???

 - (5) ????
-------------------------------------------------------
 Les Types de menu de l'application

 private boolean mainMenu;   // 1 if Main Menu , 0 default
 int menuType;           // 0: option menu ; 1: data Input Menu ; 2:Response Menu

 -------------------------------------------------------
 
3 applications :

 App 1 : Ussd master : Création de Account
                       Creation de User
                       Creation de service
                       Creation de service code
                       Craetion de Covrage
                       Creation de RouteMno

                       Statistiques 


App 2 : ServiceClient (pilotage via API ): Creation de Menu
                                           Creation d'option
                                           Creation de services annexes liées à une option
ce service va exécuter le service USSD scpécifique ( dans MeunUssdService)




App3 : MnoService : Integration de l'api de l'operateur mobile

       - reception des requetes from MNo
       - enregistrement BD
       - envoi requete à App3 et reception de response  ***ici***
       - Formatage de la reponse pour le MNO
       - Envoi des reponses au MNO
