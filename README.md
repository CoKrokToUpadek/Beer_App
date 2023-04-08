**Basics and settings**

Backend of Java Spring Web application that allows to load recipes for beers and meals from two different API,
stores them in local MySQL db, and all allows user to create account, check recipes lists, add recipes to favorite.

There are also endpoints for administrative purpose, for instance changing user status, delete meals and beers from db,
manually update some records etc. The application also have scheduler for automatic db update, but any functionalities 
related to emails were commented out for simplicity of deployment.

If one wish to activate those functionalities, he needs to uncomment related classes and configure it this way:
To run application requires basic mailtrapper configuration, as well as creating system Environment variables (or you can
just put them as regular variables if you not upload code anywhere) to fill
in application.properties:

spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
admin.mail.email_address=${MAIL_ADDRESS}

App also requires to config MySqlDb in application.properties and application-LiveDb.properties.(tests on live db are
disabled, but better safe than sorry). Application uses H2db for tests (they were also disabled
for deployment streamlining) but as long as dependencies are in build.gradle,
it should not require any changes.

**Additional information**

Beer side of implementation is overcomplicated. As I was building entities etc. I decided to recreate data from Beer API as closely 
as possible (for learning purpose) even though it was not needed. That lead to really weird functionalities, for instance,
there are functions that optimize data in db based on duplicate values in "end nodes" of db or adding values into fields in entities that were
nulls in web API but should not be null from logical point of view.    

**Link to backend:**
[Frontend for this app](https://github.com/CoKrokToUpadek/BeersAndMealsAppFront-)


