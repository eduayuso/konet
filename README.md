![moko-network](img/konet-logo.png)  
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Download](https://api.bintray.com/packages/eduayuso/kolibs/konet/images/download.svg) ](NOT RELEASED) ![kotlin-version](https://img.shields.io/badge/kotlin-1.3.70-orange)

# Kotlin multiplatform network library
This is a Kotlin MultiPlatform library (iOS & Android) that relies on [ktor-client](https://github.com/ktorio/ktor) providing a network abstraction layer to access RESTful APIs easily, so like other libraries as Retrofit (Android) or Alamofire (iOS).
[IN DEVELOPMENT]

## Table of Contents
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Samples](#samples)
- [License](#license)

## Features
- Api request abstractions:
- GET
- PUT (TODO)
- PATCH (TODO)
- POST (TODO)
- DELETE (TODO)

## Requirements
- Gradle version 5.6.4+
- Android API 16+
- iOS version 9.0+

## Installation
root build.gradle  
```groovy
buildscript {
    repositories {
        maven { url = "https://dl.bintray.com/eduayuso/kolibs" }
    }

    dependencies {
        classpath "dev.eduayuso.kolibs:konet:0.6.0"
    }
}

allprojects {
    repositories {
        maven { url = "https://dl.bintray.com/eduayuso/kolibs" }
    }
}
```

project build.gradle
```groovy
apply plugin: "dev.eduayuso.kolibs.konet" //
```

## Usage
TODO

## Samples
In source code (/konet/sample/)

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
