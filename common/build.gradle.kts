import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.0.0-rc4"
    kotlin("plugin.serialization") version "1.5.31"
    id("com.android.library")
    id("kotlin-parcelize")
}

group = "com.qwert2603.multi_public"
version = "1.0"

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
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                // todo: version variables
                api("io.ktor:ktor-client-core:1.6.5")
                api("io.ktor:ktor-client-cio:1.6.5")
                api("io.ktor:ktor-client-serialization:1.6.5")
                api("io.ktor:ktor-client-logging:1.6.5")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

                api("io.insert-koin:koin-core:3.1.3")
                api("com.arkivanov.decompose:decompose:0.4.0")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.4.0")
                api("androidx.core:core-ktx:1.7.0")
                api("io.coil-kt:coil-compose:1.4.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

// todo: common config
android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}