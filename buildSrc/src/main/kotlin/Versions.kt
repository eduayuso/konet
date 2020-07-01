/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Versions {
    object Android {
        const val compileSdk = 28
        const val targetSdk = 28
        const val minSdk = 16
    }

    const val kotlin = "1.3.70"

    private const val konet = "0.1.0"

    object Plugins {
        const val android = "3.6.1"
        const val kotlin = Versions.kotlin
        const val konet = Versions.konet
    }

    object Libs {
        object Android {
            const val glide = "4.9.0"
            const val appCompat = "1.1.0"
            const val lifecycle = "2.0.0"
        }

        object MultiPlatform {
            const val serialization = "0.20.0"
            const val coroutines = "1.3.4"
            const val ktorClient = "1.3.2"
            const val ktorClientLogging = ktorClient
            const val konet = Versions.konet
            const val mokoMvvm = "0.6.0"
        }
    }
}