package com.qwert2603.multi_public.common.presentation.post_comments

import kotlinx.coroutines.flow.StateFlow

interface PostComments {

    val state: StateFlow<PostCommentsState>

    fun onRetryClicked()

    fun onBackClicked()
}