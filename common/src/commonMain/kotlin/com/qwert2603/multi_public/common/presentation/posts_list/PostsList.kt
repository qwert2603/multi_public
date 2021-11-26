package com.qwert2603.multi_public.common.presentation.posts_list

import kotlinx.coroutines.flow.StateFlow

interface PostsList {

    val state: StateFlow<PostsListState>

    fun onRetryClicked()

    fun onPostClicked(id: Long)
}