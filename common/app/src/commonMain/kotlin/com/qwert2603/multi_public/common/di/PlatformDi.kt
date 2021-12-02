package com.qwert2603.multi_public.common.di

import org.koin.core.module.Module

expect class PlatformDi {
    fun createModule(): Module
    fun initApp()
}