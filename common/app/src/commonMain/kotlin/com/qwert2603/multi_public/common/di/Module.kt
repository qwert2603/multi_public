package com.qwert2603.multi_public.common.di

import com.qwert2603.multi_public.common.data.CommentsMapper
import com.qwert2603.multi_public.common.data.PostsMapper
import com.qwert2603.multi_public.common.data.PostsService
import com.qwert2603.multi_public.common.domain.PostsInteractor
import com.qwert2603.multi_public.common.util.urlLauncherDefinition
import com.qwert2603.multi_public.util.AppCoroutineScope
import org.koin.dsl.module

fun appModule() = module {
    single { PostsService(get(), get()) }
    factory { PostsMapper() }
    factory { CommentsMapper() }

    factory { PostsInteractor(get()) }

    factory(definition = urlLauncherDefinition)

    single { AppCoroutineScope() }
}