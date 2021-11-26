package com.qwert2603.multi_public.common.presentation.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.qwert2603.multi_public.common.presentation.post_comments.PostComments
import com.qwert2603.multi_public.common.presentation.posts_list.PostsList

interface Root {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class List(val component: PostsList) : Child()
        class Comments(val component: PostComments) : Child()
    }
}