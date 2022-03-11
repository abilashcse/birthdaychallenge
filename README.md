# Birthday app challenge

Android app that fetch a random users from the end point https://randomuser.me/api/?results=1000&seed=chalkboard&inc=name,dob 
It then sorts the user by date of birth from current date. 
Note: This project's Gradle plugin requires Java 11 to run.

# Project Architecture
This repository contains a kotlin project based on MVVM architecture. 

## Project structure

<img width="226" alt="Screenshot 2022-03-11 at 11 36 39" src="https://user-images.githubusercontent.com/62835354/157862675-8013011e-a84a-44ab-8273-7aea36b965e5.png">

* api: [Retrofit](https://square.github.io/retrofit/) is integrated to the project to enable ease of integrating API endpoints.
* data: The data folder is used to store the models for the project.
* di: For dependency Injection [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is integrated 
* ui: [Jetpack](https://developer.android.com/jetpack/compose) compose is used to create the UI for the project.
* viewmodels: view models can be added in this folder.

## Architecture diagram

![Screenshot 2022-03-11 at 14 53 04](https://user-images.githubusercontent.com/62835354/157891102-7442eeec-3561-4440-96fb-da9282a662c1.png)
