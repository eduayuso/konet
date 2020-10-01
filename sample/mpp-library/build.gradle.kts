/*
 * Use of this source code is governed by the Apache 2.0 license.
 * based on IceRock: moko-network
 */

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.kotlinSerialization)
    plugin(Deps.Plugins.mobileMultiplatform)
    plugin(Deps.Plugins.iosFramework)
}

framework {
    export(Deps.Libs.MultiPlatform.mokoMvvm)
}

dependencies {
    commonMainImplementation(Deps.Libs.MultiPlatform.coroutines) {
        // we should force native-mt version for ktor 1.4.0 on iOS
        isForce = true
    }
    commonMainImplementation(Deps.Libs.MultiPlatform.ktorClient)
    commonMainImplementation(Deps.Libs.MultiPlatform.ktorClientLogging)
    commonMainImplementation(Deps.Libs.MultiPlatform.kotlinSerialization)
    commonMainImplementation(Deps.Libs.MultiPlatform.mokoNetwork)
    commonMainApi(Deps.Libs.MultiPlatform.mokoMvvm.common)
    commonMainImplementation(Deps.Libs.MultiPlatform.konet)

    androidMainImplementation(Deps.Libs.Android.lifecycle)

    // temporary fix of https://youtrack.jetbrains.com/issue/KT-41821
    commonMainImplementation("org.jetbrains.kotlinx:atomicfu:0.14.4")
}