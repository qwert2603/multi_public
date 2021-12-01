plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(kotlin("script-runtime"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation("com.android.tools.build:gradle:7.0.0")
}