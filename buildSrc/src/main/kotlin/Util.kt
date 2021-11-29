import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

inline val PluginDependenciesSpec.`jetbrains-compose`: PluginDependencySpec
    get() = id("org.jetbrains.compose") version Versions.jetbrainsCompose

inline val PluginDependenciesSpec.`plugin-serialization`: PluginDependencySpec
    get() = kotlin("plugin.serialization") version Versions.pluginSerialization

inline val PluginDependenciesSpec.`common-module`: PluginDependencySpec
    get() = id("commonModule")