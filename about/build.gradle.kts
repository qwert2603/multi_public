import org.jetbrains.compose.compose

plugins {
    `common-module`
    `jetbrains-compose`
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
            }
        }
    }
}