package com.qwert2603.multi_public.common.presentation.posts_list

import kotlinx.coroutines.flow.StateFlow

interface PostsList {

    val state: StateFlow<PostsListState>

    fun onRefresh()

    fun onRetryClicked()

    fun onPostClicked(id: Long)

    fun onOpenPostWebClicked(id: Long)

    fun onOpenLinkClicked(url: String)
}