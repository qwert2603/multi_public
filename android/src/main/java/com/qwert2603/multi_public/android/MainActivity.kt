package com.qwert2603.multi_public.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import com.qwert2603.multi_public.common.App
import com.qwert2603.multi_public.common.presentation.root.RootComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val componentContext = defaultComponentContext()
            val rootComponent = RootComponent(componentContext)
            App(rootComponent)
        }
    }
}