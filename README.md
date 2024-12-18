# Cat Breeds ShowCase

This is a sample project for Android, using Kotlin and Jetpack Compose.
It fetches a list of cat breeds from an API and displays them in a list.

## Features

* Fetches a list of cat breeds from an API
* Displays the list of cat breeds in a list
* Allows the user to favorite a specific cat breed and persist it in DataStore
* Displays the details of a selected cat breed

## Architecture

The project uses the following architecture components:

* Clean Architecture with MVVM pattern for organizing the codebase
* Solid Principles to ensure maintainable and scalable code
* DataStore for storing the list of favorite cat breeds
* Retrofit for making API calls
* Kotlin Coroutines for handling asynchronous operations
* Jetpack Compose for the UI
* Currently only 2 unit tests have been added for time concerns

## Building

The API key used in this project is stored in the `gradle.properties` file. To build the project,
you need to replace the API key with your own key.

## Enhancements

* More unit tests can be added to ensure the correctness of the codebase
* Integration tests can be added to ensure the correctness of the integration between the different components
* UI tests can be added to ensure the correctness of the UI
* Instead of getting all the data at once, can use paging to fetch the data from the network
* Can have an offline first approach and use Room for storing the data locally and WorkManager for syncing the data with the network
* Can use modularization, but the preferred approach is to have a layer based separation for simplicity and not over engineering
* UI and UX can be improved to make the app more visually appealing and user friendly