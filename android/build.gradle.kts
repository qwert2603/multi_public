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
}

android {
    compileSdkVersion(Configs.compileSdk)
    defaultConfig {
        applicationId = Configs.applicationId
        minSdkVersion(Configs.minSdk)
        targetSdkVersion(Configs.targetSdk)
        versionCode = Configs.versionCode
        versionName = Configs.versionName
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false // todo: set true and test
        }
    }
}