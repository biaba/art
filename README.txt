Backend project for AngularApp

Used technologies:
- Java 1.8.0_271
- Spring Boot Security 5.4.5.
- Hibernate ORM core 5.4.28.Final
- MySql 8.0.23
- Apache Maven 3.6.1
- Tomcat 9.0
- Eclipse 2020-03 (4.15.0)

Database scripts and exported files provided in sql_scripts folder

Description of application:
- Unregistered users can:
	search DB for artists/artwork
	search Metropolitan Art Museum for artists/artwork (doesn't interact with backend, goes straight to Met Museum REST)
	register (not completed, adding form)
	
- Registered users can:
	- add artwork to favourites
	- buy artwork (not completed, PayPal integration)
	- sell artwork (not completed, PayPal integration)


* Files/folders marked with *learning are of no practical usage. To check how app makes outgoing REST calls. 

########### TO BE DONE ##############
- profile creation
- adding https
- adding PayPal
- adding Google/FB login