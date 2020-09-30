/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Deps {

    object Plugins {

        val androidApplication      = GradlePlugin(id = "com.android.application")
        val androidLibrary          = GradlePlugin(id = "com.android.library")
        val kotlinJvm               = GradlePlugin(id = "org.jetbrains.kotlin.jvm")
        val kotlinMultiplatform     = GradlePlugin(id = "org.jetbrains.kotlin.multiplatform")
        val kotlinKapt              = GradlePlugin(id = "kotlin-kapt")
        val kotlinAndroid           = GradlePlugin(id = "kotlin-android")
        val kotlinAndroidExtensions = GradlePlugin(id = "kotlin-android-extensions")
        val kotlinSerialization     =  GradlePlugin(
            id = "org.jetbrains.kotlin.plugin.serialization",
            module = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
        )
        val mavenPublish            = GradlePlugin(id = "org.gradle.maven-publish")
        val mobileMultiplatform     = GradlePlugin(id = "dev.icerock.mobile.multiplatform")
        val iosFramework            = GradlePlugin(id = "dev.icerock.mobile.multiplatform.ios-framework")
        val detekt                  = GradlePlugin(id = "io.gitlab.arturbosch.detekt", version = "${Versions.Libs.detekt}")
    }

    object Libs {

        object Android {
            val appCompat           = "androidx.appcompat:appcompat:${Versions.Libs.appCompat}"
            val lifecycle           = "androidx.lifecycle:lifecycle-extensions:${Versions.Libs.lifecycle}"
            val coroutines          = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.coroutines}"
            val ktorClientOkHttp    = "io.ktor:ktor-client-okhttp:${Versions.Libs.ktorClient}"
        }

        object Ios {
            val ktorClientIos       = "io.ktor:ktor-client-ios:${Versions.Libs.ktorClient}"
        }

        object Detekt {
            val detektFormatting    = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.Libs.detekt}"
        }

        object MultiPlatform {
            val coroutines          = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Libs.coroutines}"
            val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Libs.serialization}"
            val ktorClient          = "io.ktor:ktor-client-core:${Versions.Libs.ktorClient}"
            val ktorClientLogging   = "io.ktor:ktor-client-logging:${Versions.Libs.ktorClient}"
            val mokoNetwork         = "dev.icerock.moko:network:${Versions.Libs.mokoNetwork}"
            val konet               = "dev.eduayuso.kolibs:kontet:${Versions.Libs.konet}"
            val mokoMvvm = MultiPlatformLibrary(
                common = "dev.icerock.moko:mvvm:${Versions.Libs.mokoMvvm}",
                iosX64 = "dev.icerock.moko:mvvm-iosx64:${Versions.Libs.mokoMvvm}",
                iosArm64 = "dev.icerock.moko:mvvm-iosarm64:${Versions.Libs.mokoMvvm}"
            )
        }

        object Tests {
            val kotlinTestJUnit       = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
            val androidCoreTesting    = "androidx.arch.core:core-testing:2.1.0"
        }
    }
}