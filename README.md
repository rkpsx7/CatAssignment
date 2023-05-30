# Cats (Assignment@Lokal)
 
 ![GitHub Cards Preview](https://raw.githubusercontent.com/rkpsx7/CatAssignment/master/GithubResources/Keep%20track%20of%20your%20expenses%20and%20incomes%20easily%2C%20rapidly%20and%20securely(1).png)

## Features & Description:
- **Offline-Caching** using RoomDB - Data is stored in room db to provide offline app usage.
- **Data is fetched in pages** as user scrolls down, more pages will be fetched.
- Fliter cats data by breeds - filters are saved in DB and used to provide suggestions during search.
    
    **Flow of Filter ->** when user inputs a "breed keyword", all cats information is fetched from api and saved in DB. Results are provided from DB(Single Souce of Truth in this app) to UI

## Tech Stack ✨
- Android
- Kotlin
- Paginantion - Paging3
- Kotlin-Flows
- Kotlin-Coroutines
- MVVM-Architecture
- RoomDB, Type Convertors(storing complex data in DB)
- Retrofit 2
- Dagger-Hilt (DI)

## [Click here to try the APP](https://github.com/rkpsx7/CatAssignment/raw/master/GithubResources/direct-run.apk)🌅

## Instructions to clone this project ✌
```bash
  open Terminal or CMD (Windows)
  paste this command given below in Terminal
  command:- git clone https://github.com/rkpsx7/CatAssignment.git
  OR

  Open Android Studio
  Click -> File > New > Project From Version Control
 
  and paste the url in URL box

  URL :- https://github.com/rkpsx7/CatAssignment.git
```





