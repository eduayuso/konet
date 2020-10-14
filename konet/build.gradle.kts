/*
 * Use of this source code is governed by the Apache 2.0 license.
 * based on IceRock: moko-network
 */

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.mobileMultiplatform)
    plugin(Deps.Plugins.mavenPublish)
}

group = Products.Konet.groupId
version = Products.Konet.version

kotlin {
    sourceSets {
        val iosArm64Main by getting
        val iosX64Main by getting

        iosArm64Main.dependsOn(iosX64Main)
    }
}

dependencies {

    commonMainApi(Deps.Libs.MultiPlatform.kotlinSerializationCore)
    commonMainApi(Deps.Libs.MultiPlatform.kotlinSerializationJson)
    commonMainApi(Deps.Libs.MultiPlatform.ktorClient)
    commonMainImplementation(Deps.Libs.MultiPlatform.ktorClientLogging)
    commonMainImplementation(Deps.Libs.MultiPlatform.mokoNetwork)

    androidMainApi(Deps.Libs.Android.ktorClientOkHttp)
    iosMainApi(Deps.Libs.Ios.ktorClientIos)

    androidMainImplementation(Deps.Libs.Android.appCompat)
}

publishing {
    repositories.maven("https://api.bintray.com/maven/eduayuso/kolibs/konet/;publish=1") {
        name = "bintray"

        credentials {
            username = "eduayuso"
            password = ""
        }
    }
}