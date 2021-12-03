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
            dependencies {
                api(Deps.KotlinX.Coroutines.core)
            }
        }
        val androidMain by getting {
        }
        val desktopMain by getting {
        }
    }
}

android {
    compileSdk = Configs.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        consumerProguardFile("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}