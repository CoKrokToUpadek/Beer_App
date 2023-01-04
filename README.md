**Basics and settings**

Backend of Java Spring Web application that allows to load recipes for beers and meals from two different API,
stores them in local MySQL db, and all allows user to create account, check recipes lists, add recipes to favorite.

There are also endpoints for administrative purpose, for instance changing user status, delete meals and beers from db,
manually update some records etc. The application also have scheduler for automatic db update.

To run application requires basic mailtrapper configuration, as well as creating system Environment variables (or you can
just put them as regular variables if you not upload code anywhere) to fill
in application.properties:

spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
admin.mail.email_address=${MAIL_ADDRESS}

and also requires to config MySqlDb in application.properties and application-LiveDb.properties.(tests on live db are
disabled, but better safe than sorry). Application uses H2db for tests but as long as dependencies are in build.gradle,
it should not require any changes.

Also, backend runs on standard localhost base port (8080), while front runs on 8082.

**Additional information**

Application have some weird things in it and il try to explain some of them here:

1. Test coverage is slightly below 65% on code, because of service classes that are just there to increase abstraction level
in case I had to make some modification/validation of data from and to repositories, and since most of the time it was not needed, 
functions inside services had simple one-liners from repositories that don't really require testing.

2. There was requirement for two design patterns. I used facade as one. The second one was singleton for mapper (as it's suggested by
 documentation), but while writing the code it was more and more obvious that im kinda forcing it, so now mapper is a bean that have
configuration class invoked while creating bean, and since beans are singletons by default, it's still is a singleton.

3. Beer side of implementation is overcomplicated. As I was building entities etc. I decided to recreate data from Beer API as closely 
as possible (for learning purpose) even though it was not needed. That lead to really weird functionalities, for instance,
there are functions that optimize data in db based on duplicate values in "end nodes" of db or adding values into fields in entities that were
nulls in web API but should not be null from logical point of view. 

4. Mapper for Meals have 20 if inside. It's because in web API data is stored in 20 fields instead of list, so it's not my fault.

5. I couldn't figure out JWT, so login verification while connecting to frontend is ugly.

6. Overall all functionalities should work via postman.

**Link to backend:**
[Frontend for this app](https://github.com/CoKrokToUpadek/BeersAndMealsAppFront-/tree/FrontendForReview)


