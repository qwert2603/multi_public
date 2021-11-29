plugins {
    `kotlin-multiplatform`
    com.android.library
    `kotlin-parcelize`
}

group = Configs.group
version = Configs.versionName

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
        }
        val androidMain by getting {
        }
        val desktopMain by getting {
        }
    }
}

android {
    compileSdkVersion(Configs.compileSdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Configs.minSdk)
        targetSdkVersion(Configs.targetSdk)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}