
Alex WIttebrood 10288880

#GuidiConnect

Guidiconnect is an application platform where tourist are able to get in contact with locals in other citys before they are in the city. It is possible to search for specific users from the city you are travelling to. 
Most of the time when you travel you wander the tourist route and see more foreigners then locals. While the most beautifull spots aren't touristic at all. But you first need to know where to go. There is where GuidiConnect comes in Guidi (italian for guide) will get you in contact with locals before you are even in the city.

When the tourist(User) finds the right localguide account it can send a request to the  guide if they want to get in contact. If the Guide accepts the request the guide account is saved in to the guides "Matches" and the guide is able to immediatley send an email to the tourist to get in contact.
And the fun part it works visa versa ofcourse, because each tourist is a guide in his own city!

#Design

#### Overview with the classes and the methods. 


![alt text](https://github.com/alexwit/project-guiri/blob/master/doc/newClassOverview%20(1).JPG "New Class Overview")

### Activities
*Register: This is were you can create your own unique parse account.
*Login: If no User is logged in yet, here is were you can login.
*NewAccount: Extra information is needed when a new user joins.
*MainActivity: This is where the search takes place.
*Requests: In the Requests can the Guide see an overview with all user requests. 
*SearchList: Overview with all accounts of that specific city. 
*GuideAccount: Overview with the guides information.
*AcceptGuide: Overview with all requests that the guide accepted.
*Account: Overview with your own account details.
*ChangeAccount: possibility to change account information.

### Classes
* Register - Creates an unique account in the Parse Back-end and checks if the input is correct.
* Login - Checks if the login account info is correct when the user allready has an account.
* NewAccount - Makes sure that all necessary data is given by the user in a correct form.
* MainActivity - Asks for the city the user want's to meet people.
* Search - Depending which account information is given, sets constraints and returns the correct information from the Parse Back-end. It can search in Parse for users that live in a certain city.
* SearchList - Shows all accounts that live in the city asked for.
* ProfileAdapter - Makes sure each item sends you through to the specific account.
* GuideAccount - shows all information of the asked guide and gives the possibility to send a request. When a request is send All data of the currentUser(tourist) and the otherUser(Guide) are saved in the database.
* Requests - Shows all accounts that have requested to get in contact with you.
* RequestAdapter - Makes sure each item(account) is possible to decline or to accept the request of the user. If you decline the request it is impossible to get in contact with the user. If you accept the request of the user you(Guide) will be able to send an email to the user from your Matches activity.
* OnDataChanged(Interface) - Makes sure that it is not possible to accept or decline the same user multiple times. It will refresh the data input and makes sure the list contains the correct requests of the users.
* AcceptGuide - Shows a list with all the accounts that you(Guide) have accepted and want to get in contact with.
* AcceptAdapter - Makes sure the guide is able to send a email to the tourist.
* Account - shows all information of the current user
* ChangeAccount - makes it possible to change information.
 

#### Sketches that show the UI 

![alt text](https://github.com/alexwit/project-guiri/blob/master/doc/Overzichtscreens.jpg "Overview Start screens")

In the picture above you can see everything that happens if you allready have an account at the GuidiConnect app.

![alt text](https://github.com/alexwit/project-guiri/blob/master/doc/overzichtrequestmatches.JPG "Request and matches screens")

In the picture above you can see the possibility to send an email when you accept a request of a tourist user.


# Challenges and Solutions

During the developing progress a lot of brickwalls were hit and challenges that needed to be overcome. Most of them were with the Back-end account system of Parse. 

In the beginning I had no clue what was happening on the Back-end of Parse.
I originally created my app before trying to implement Parse. But this allready took me a full day because it couldn't use the SDK of Parse. After a day of struggling I gave this up and started a new App throughout the Parse website which then worked. Originaly I planned to keep all information of the user except the username, email and password in a seperated database because I thought it would be better protected this way. But it was nearly impossible for me to query cross specific information of users. 

From that point on I started to store all information in my secundary database but still I had a though time calling the correct objectId of other users. One of the reasons of not finding any users was that the user database is called on as "_User". 

I also noticed that it gave a lot of complications when a user didn't give all information immediatley from the start. That's why I implemented the NewAccount class where all information necessary for searching is asked for. 

Also in the beginning I didn't know the difference between ParseUsers and ParseObjects and how the authentication and security's where build in by Parse. After struggling with the secured "_User" database of parse I was confronted with no returns on queries ever. After a day I noticed that all users where protected. I wanted to protect the users but also wanted the other users to sendRequests and add information (reviews) and MatchRequests to another account which was not possible within the _User database. The first thing I tried was to set up a relation between user through pointer values. Each time a request was send I would add a row to the database where TouristID en GuideID was connected to the _User database with a boolean of request accepted. But every time I tried to get the user information through the pointer it wouldn't return any information. After two days I gave up and created a new row containing all information of the Guide and the Tourist.  


Another huge obstacle to overcome was getting all user information into a Dynamic listview. I found on internet and the Parse website that when using ParseUser information it should be representend by CustomAdapter which calls a ParseQueryAdapter.QueryFactory which should be really easy to implement. To use this QueryFactory you needed to add some lines to de graddle build which was no where mentioned on Parse site. I also tried to make the custom adapter through another way but I wasn't able to get the queries right to get the information. The hard part was the minimal ammount of documentation about QueryFactory. 

When I tried to put in a user Specific push notification system I got completly lost in the documentation and examples. And found on the internet it could be done but would be really hard to fix. In the end I found out another way of getting users to contact each other, by an email intent which was really easy to implement. 

Also I wanted to implement a large xml file to make sure all city's and country's would be spelled correct and set right. But because of long struggles with ListViews and the Parse back-end. I didn't have enough time to implement this. In the next release I know it will be in the app.

Another thing I wanted to implement was a Review and Rating system. So it would be easier to pick a good guide and filter out the bad ones through bad ratings. I tried this by putting the reviews in an HashMap with the uniqueID of the user as a Key and storing the reviews as Values. But also because of time shortage and malfcuntion I needed to remove this from the app. 







