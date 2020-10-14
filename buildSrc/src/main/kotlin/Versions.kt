/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object Versions {

    object Android {
        const val compileSdk = 29
        const val targetSdk = 29
        const val minSdk = 21
    }

    const val kotlin = "1.4.10"

    object Plugins {
        const val android = "3.6.1"
        const val mokoNetwork = "0.8.0"
        const val konet = Products.Konet.version
    }

    object Libs {

        const val appCompat = "1.2.0"
        const val lifecycle = "2.2.0"
        const val serialization = "1.0.0"
        const val coroutines = "1.4.0-M1"
        const val ktorClient = "1.4.0"
        const val konet = Products.Konet.version
        const val mokoMvvm = "0.8.0"
        const val mokoNetwork = "0.8.0"
        const val detekt = "1.7.4"
    }
}