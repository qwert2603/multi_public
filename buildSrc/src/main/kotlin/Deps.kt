object Deps {

    object KotlinX {
        const val serializationJson =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinX.serializationJson}"
    }

    object AndroidX {
        const val activityCompose = "androidx.activity:activity-compose:${Versions.AndroidX.activityCompose}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val cio = "io.ktor:ktor-client-cio:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
    }

    object Decompose {
        const val decompose = "com.arkivanov.decompose:decompose:${Versions.decompose}"
        const val extensionsComposeJetbrains =
            "com.arkivanov.decompose:extensions-compose-jetbrains:${Versions.decompose}"
    }

    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"

    const val junit = "junit:junit:${Versions.junit}"
}