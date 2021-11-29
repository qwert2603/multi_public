pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

}
rootProject.name = "multi_public"


include(":android")
include(":desktop")
include(":common")
include(":about")
include(":common:util")
