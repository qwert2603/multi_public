plugins {
    `jetbrains-compose`
    com.android.application
    `kotlin-android`
}

group = Configs.group
version = Configs.versionName

dependencies {
    implementation(project(Modules.common))

    implementation(Deps.AndroidX.activityCompose)
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.coreKtx)
}

android {
    compileSdk = Configs.compileSdk
    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName(BuildType.debug) {
            applicationIdSuffix = ".debug"
            versionNameSuffix = ".snapshot"
        }
        getByName(BuildType.release) {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}