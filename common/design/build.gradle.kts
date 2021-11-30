import org.jetbrains.compose.compose

plugins {
    `common-module`
    `jetbrains-compose`
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.util))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.coilCompose)
            }
        }
    }
}