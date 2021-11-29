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
                implementation(project(Modules.util))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)

                implementation(Deps.Ktor.core)
                implementation(Deps.Ktor.cio)
                implementation(Deps.Ktor.serialization)
                implementation(Deps.Ktor.logging)
                implementation(Deps.KotlinX.serializationJson)

                implementation(Deps.Koin.core)
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Decompose.extensionsComposeJetbrains)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.AndroidX.appcompat)
                implementation(Deps.AndroidX.coreKtx)
                implementation(Deps.coilCompose)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.preview)
            }
        }
    }
}