[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Download](https://api.bintray.com/packages/eduayuso/kolibs/konet/images/download.svg) ](https://bintray.com/eduayuso/kolibs/konet/1.0.0) ![kotlin-version](https://img.shields.io/badge/kotlin-1.3.72-orange)

# Kotlin multiplatform network library
Konet is a Kotlin MultiPlatform library (iOS & Android) that provides a network abstraction layer to request APIs over HTTP easily, so like other libraries as [Retrofit (Android)](https://github.com/square/retrofit) or [Alamofire (iOS)](https://github.com/Alamofire/Alamofire).
This library is a wrapper over [ktor-client](https://github.com/ktorio/ktor) that make easier to use the ktor client features, and letting you to customize the logging, http interceptos and all
other Ktor features if you need something more advanced than the default Konet configuration.

The Konet library has been built using the network utils library of [moko-network](https://github.com/icerockdev/moko-network). Moko-network, by [IceRock](https://github.com/icerockdev) is a useful Kotlin mpp library that includes an API client source generator from an OpenAPI specification using Swagger (the generator feature is not included in Konet)

Note: I encourage you to built your multiplatform solution using the [moko-template](https://github.com/icerockdev/moko-template), which comes along with a lot of very useful libs and utils to develop modular and scalable Kotlin multiplatform projects. 

## Table of Contents
- [How to install](#installation)
- [How to use](#usage)
- [License](#license)

## Installation
root build.gradle  
```groovy
buildscript {
    repositories {
        maven { url = "https://dl.bintray.com/eduayuso/kolibs" }
    }

    dependencies {
        classpath "dev.eduayuso.kolibs:konet:1.0.2"
    }
}

allprojects {
    repositories {
        maven { url = "https://dl.bintray.com/eduayuso/kolibs" }
    }
}
```

## Usage
Basic example: How to make a request to a fake API [https://reqres.in/](https://reqres.in/) in a MVVM / MVP project using Kotlin coroutines.

##### 1.- Create your API client. You can create this property inside a SharedFactory class in your mpp-library
```kotlin
val sampleApi by lazy {

    KoApiClient(url = "https://reqres.in/api/")
}
```

##### 2.- Create your Repository. (We recommend to use the repository pattern - more details in sample sources)
 ```kotlin
interface IUsersRepository {
    
    suspend fun getUserById(id:Int): UserModel?
}
```
```kotlin
class UsersRepository(api:KoApiClient): IUsersRepository {
    
    /** 
     * This makes a GET request to: https://reqres.in/users/{id}.
     * We need to provide the model serializer to the response resolver
     */
    override suspend fun getUserById(id:Int): UserModel? {
        
        return api.consume("users").get(id).response(UserModel.serializer())
    }
}
```
 ```kotlin
@Serializable
data class UserModel(
    var id: Int?,
    var email: String?
)
```
##### 3.- Use the UsersRepository in your viewModel to consume the API and present the results
```kotlin
class UsersViewModel(
    
    val userRepo: IUsersRepository // implementation is injected

): ViewModel() {

    fun onFetchUserClick(id:Int) {
    
        viewModelScope.launch {
            try {
                val user = userRepo.getUserById(id)
                // present your user entity
            } catch (error: Exception) {
                // catch and present the error
            }
        }
    }
}
```
##### Notes:
* In /sample folder in sources you can see a sample app for Android and for iOS
* To run the iOS sample app you need to run 'pod install' inside the /sample/ios-app folder, after
a successful build you can open the project by selecting the 'xcworkspace' file.

## License
        
    Copyright 2020 Eduardo Rodriguez Ayuso
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.