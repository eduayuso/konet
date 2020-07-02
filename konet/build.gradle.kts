/*
 * Copyright 2020 Eduardo Rodriguez Ayuso. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
    id("kotlinx-serialization")
    id("maven-publish")
}

group = Products.Konet.groupId
version = Products.Konet.version

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }
}

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.serialization)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)

    androidLibrary(Deps.Libs.Android.appCompat)
}

publishing {
    repositories.maven("https://api.bintray.com/maven/eduayuso/kolibs/konet/;publish=1") {
        name = "bintray"

        credentials {
            username = System.getProperty("user")
            password = System.getProperty("pass")
        }
    }//ads
}

// workaround while https://youtrack.jetbrains.com/issue/KT-36720 not implemented
kotlin {
    targets
        .filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>()
        .flatMap { it.compilations }
        .forEach {
            it.kotlinOptions.freeCompilerArgs += listOf("-module-name", "konet")
        }
}
