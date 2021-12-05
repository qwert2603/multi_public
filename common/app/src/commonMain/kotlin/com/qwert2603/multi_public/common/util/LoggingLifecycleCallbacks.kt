package com.qwert2603.multi_public.common.util

import com.arkivanov.essenty.lifecycle.Lifecycle

class LoggingLifecycleCallbacks(private val name: String) : Lifecycle.Callbacks {
    override fun onCreate() = println("Lifecycle: $name onCreate")
    override fun onDestroy() = println("Lifecycle: $name onDestroy")
    override fun onPause() = println("Lifecycle: $name onPause")
    override fun onResume() = println("Lifecycle: $name onResume")
    override fun onStart() = println("Lifecycle: $name onStart")
    override fun onStop() = println("Lifecycle: $name onStop")
}