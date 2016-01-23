#Day 1

Made a list with all functions needed for each class. 
Also made the choise to give the functions of chatting and a history of the things you've done a secondary priority in the process.
Made an overview with how the classes and their functions would send function with each other.
Also made an overview how the UI reacts with the different classes on the background

#Day 2

Searched information about Parse and how apps as Tinder and Uber use their account data base.
Am trying to get to know Parse better and figuring out how I will be able to implement my idea.

#Day 3 (Actually day 7)

Had problemen met het implementeren van Parse. Moet uitzoeken hoe je correct Informatie toevoegd aan de database deze weer ophaald
en weer kan veranderen. Het is nu gelukt om info toe te voegen maar dan wordt iedere keer een nieuw object aangemaakt in de database.

#Day 4
Heb ondervonden dat het downloaden van info uit de data base te doen is maar alleen met een gehardcode object. Ben er nog niet achter hoe je dit interactief kan maken. Ook heb ik bedacht dat gelijk na het Registeren het nodig is om alle info in te vullen die nodig zijn bij een account. Anders kom je iedere keer in de problemen als je de account gegevens wilt aanpassen. 

#Day 5
Het lukt om de data aan te passen en toe te voegen. Maar het lukt nog niet om een lijst met account namen te returnen van bijvoorbeeld eenspecifieke stad. Nadat ik eerst alle account informatie wilde  koppelen aan de data base. Heb ik er toch voor gekozen om bepaalde informatie als naam en stad aan het User account direct te koppelen en secundaire info als interesses en reviews in de data base te stoppen. 

#Day 6 

Het lukt om een een lijst met ParseUsers te returnen uit een specifieke stad maar het lukt nog niet om deze uit te beelden in een List View. 

#Day 7
Hang al 2 dagen bij het correct in beeld brengen van de userlist!! 

#Day 8 
Probleem in kaart brengen userlist opgelost doormiddel van nieuwe update van Parse stond de ParseQueryFactory niet meer in de standaard library maar moest hier een extra library voor worden toegevoegd. Stond erg slecht aan gegeven in de documentatie. Gelukkig kwam stackoverflow met een reactie.

#Day 9 
uit eenzetting van een queryfactory naar een list is lastig. Zeker als vervolgens uit die lijst weer de Parse informatie van dat account moet worden opgehaald.

#Day 10
Het is niet mogelijk om data te koppelen aan een item van de list. Hierdoor was het niet mogelijk om de objectId informatie van de andere user mee te geven. Als oplossing is een onclicklistener toegevoegd aan het item waardoor je gelijk wordt doorgestuurd met de account informatie en deze kan aanpassen. 

#Day 11
Hele dag aan HEURISTIEKEN GEZETEN

#Day 12 
Hele dag aan heuristieen gezeten

#Day 23
Push notification geimplementeerd maar het lukt niet om deze naar individuele accounts te sturen. Op gegeven en alle code verwijdert en terug gekeerd naar oude commit omdat code totaal overhoop was.

#Day 24
Probeer informatie aan andere accounts toe te voegen maar dat mag niet door doordat user "not authenticated" is. Moet een manier vinden zodat ik informatie aan andere users kan toepassen zodat ik afspraken kan maken. 



