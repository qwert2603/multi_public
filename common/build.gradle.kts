import org.jetbrains.compose.compose

plugins {
    `kotlin-multiplatform`
    `jetbrains-compose`
    `plugin-serialization`
    com.android.library
    `kotlin-parcelize`

    `android-common`
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
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                api(Deps.Ktor.core)
                api(Deps.Ktor.cio)
                api(Deps.Ktor.serialization)
                api(Deps.Ktor.logging)
                api(Deps.KotlinX.serializationJson)

                api(Deps.Koin.core)
                api(Deps.Decompose.decompose)
                api(Deps.Decompose.extensionsComposeJetbrains)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(Deps.AndroidX.appcompat)
                api(Deps.AndroidX.coreKtx)
                api(Deps.coilCompose)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Deps.junit)
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