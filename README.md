# CPlayers - A Case Study

## Problem Statement

Build a system to search for a cricket player, get player statistics, add players to favourite list and recommend most liked/favourite players to user.

## Requirements

1. The application needs to fetch cricket players from the following API.
https://www.cricapi.com/

Refer the following URLs to explore more on the cricket player APIs.
https://www.cricapi.com/how-to-use.aspx
https://www.cricapi.com/how-to-use/player-stats-api.aspx
https://www.cricapi.com/how-to-use/player-finder.aspx

2. A frontend where the user can register/login to the application, find cricket player, get player statistics, add player to favourite list and view recommended players.
  - On launching the application the user should get the login page. The login page should have a link for registration using which the user should be able to register. On successful registration the user should be taken to the login page. Upon login, the user should be taken to the home page.
  - Proper navigation links for all the pages should be available within pages.
  - Error handling should be implemented across pages. Appropriate messages should be    displayed for the same. Success messages should be displayed for the User Registration.
  - Logout feature should be implemented.

3. User can add a player to favourite list and should be able view the favourite list.
4. A recommendation engine/service should be able to store all the unique favourite players from all the users and maintain counter for number of users added a particular player into favourite list. 
5. An option to view recommended players should be available on frontend. 

## Microservices/Modules
- UI (User interface) -  should be able to
   - Search a player by name
   - View player statistics
   - Add a player to favourite list
   - View favourite players
   - View recommended players
   - UI should be responsive which can run smoothly on various devices 
- UserService - should be able to manage user accounts.
- FavouriteService - should be able to store all the favourite players for a user
- PlayerRecommendationService - should be able to store all the unique favourite players from all the users and maintain counter for number of users added a particular player into favourite list.


## Tech Stack
- Spring Boot
- MySQL, MongoDB
- Zuul API Gateway
- Eureka Server
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

### Building frontend:
   1. Building responsive views:
      - Register/Login
      - Search for a player
      - Player list - populating from external API
      - Build a view to show favourite players
      - Build a view to show recommended players
   2. Using Services to populate these data in views
   3. Stitching these views using Routes and Guards
   4. Making the UI Responsive
   5. Unit Tests should be created for the Components and Services
   6. E2E scripts using protractor should be created for the functional flows
   7. Implement CI - continuous Integration ( use .gitlab-ci.yml)
   8. Dockerize the frontend (create dockerfile, docker-compose.yml and get it executed through docker compose)

### Note: FrontEnd and all the backend services should be dockerized and run through docker-compose

### Building the UserService
   1. Creating a server in Spring Boot to facilitate user registration and login  with MySQL as backend. Upon login, JWT token has to be generated. It has to be used in the Filters set in other services.
   2. Writing swagger documentation
   3. Unit Testing of the entire code which involves the positive and negative scenarios
   4. Implement continuous Integration CI ( use .gitlab-ci.yml)
   5. Dockerize the UserService (create dockerfile, docker-compose.yml and get it executed through docker compose)

### Create an API Gateway which can serve UI and API Request from same host.

### Apply JWT Authentication in the API Gateway for all the incoming API requests.

### Building the Favourite Service
   1. Building a server in Spring Boot to facilitate CRUD operation over favourite players stored in MongoDB
   2. Writing Swagger Documentation
   3. Build a Producer for RabbitMQ which
      i. Produces events like what user added to favourite list
   4. Write Unit Test Cases and get it executed.
   5. Implement CI - continuous Integration ( use .gitlab-ci.yml)
   6. Dockerize the service(create dockerfile and update the docker-compose.yml)


### Building the PlayerRecommendationService
   1. Building a Consumer for RabbitMQ
      - i. On a new event generated Update the recommendations in the system. Store the recommendation list in MongoDB.
      - ii. Maintain list of unique recommended players based on what user added into favourite list and keep counter for number of users added a particular player into favourite list
   2. Build an API to get Recommendations
   3. Writing Swagger Documentation
   4. Write Unit Test Cases and get it executed.
   5. Implement CI - continuous Integration ( use .gitlab-ci.yml)
   6. Dockerize the service(create dockerfile and update the docker-compose.yml)
   7. Update the API Gateway

### Create Eureka server and make other services as client

### Demonstrate the entire application
    1. Make sure all the functionalities are implemented
    2. Make sure both the UI (Component and Services) and server side (For all layers) codes are unit tested. 
    3. All the Services are up and running using docker (Dockercompose should be used for running them)
    4. Eureka server should be running and all the required services are registered and started
    5. Rabbit-MQ service should be running ( check whether exchanges are created)
    6. E2E tests should have been created and can be executed successfully
    7. Application is completely responsive in nature