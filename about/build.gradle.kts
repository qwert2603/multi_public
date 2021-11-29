import org.jetbrains.compose.compose

plugins {
    `common-module`
    `jetbrains-compose`
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
    }
}