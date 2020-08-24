# Library

## Overview
This microservice will manage the library. It will allow to borrow and return the books.

Maven module approach is used in the microservice which contains below modules:
- library-spec (Specification of  microservice)
- library-service (Actual logic implementation)

pom.xml in root level is parent pom
 
## Approach Used
Test driven development

## Assumptions
1) No provision to add books in the library
2) No Provision to add the users as a member for library
3) No database is used instead a all the  data is kept in static list (in JVM)

## Service setup and run using command prompt
1) Once the service checked out from git execute following command at root level in command prompt
````bash
mvn clean install
````
2) To run service go to service folder in command prompt and run below command
````bash
mvn spring-boot:run
````
## Service setup and run in IDE
1) Open LibraryApi.java class and run project as java application
2) One can also run maven goal run from IDE to start the serice

## Details of service
By default the service will run on the port 8080. Once the service starts use below url to see the detials of API

http://localhost:8080/swagger-ui.html

## Other details
Get all book from library endpoint is reactive endpoint (for better performace) to get all the books in library

## Note
Feel free to provide any review comments or improvements 