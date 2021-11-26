package com.qwert2603.multi_public.common.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.qwert2603.multi_public.common.presentation.post_comments.PostCommentsUi
import com.qwert2603.multi_public.common.presentation.posts_list.PostsListUi
import com.qwert2603.multi_public.common.util.allCases

@Composable
fun RootUi(component: Root) {
    Children(component.routerState) {
        when (val child = it.instance) {
            is Root.Child.List -> PostsListUi(child.component)
            is Root.Child.Comments -> PostCommentsUi(child.component)
        }.allCases
    }
}