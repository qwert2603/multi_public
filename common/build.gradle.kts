import org.jetbrains.compose.compose

plugins {
    `common-module`
    `jetbrains-compose`
    `plugin-serialization`
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.about))

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
        val androidMain by getting {
            dependencies {
                api(Deps.AndroidX.appcompat)
                api(Deps.AndroidX.coreKtx)
                api(Deps.coilCompose)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
    }
}