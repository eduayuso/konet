plugins {
    plugin(Deps.Plugins.androidApplication)
    plugin(Deps.Plugins.kotlinAndroid)
    plugin(Deps.Plugins.kotlinKapt)
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    buildFeatures.dataBinding = true

    dexOptions {
        javaMaxHeapSize = "2g"
    }

    defaultConfig {

        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)

        applicationId = Products.SampleApp.appId
        versionCode = Products.SampleApp.versionCode
        versionName = Products.SampleApp.versionName

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    lintOptions {
        disable("Instantiatable") // bug Error: SimpleActivity must extend android.app.Activity [Instantiatable]
    }
}

dependencies {
    implementation(Deps.Libs.Android.appCompat)
    implementation(Deps.Libs.Android.lifecycle)

    implementation(project(":sample:mpp-library"))
}