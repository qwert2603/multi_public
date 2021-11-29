package com.qwert2603.multi_public.common.presentation.posts_list

import com.qwert2603.multi_public.common.domain.Post
import com.qwert2603.multi_public.util.LoadingState

data class PostsListState(
    val postsListLoadingState: LoadingState<Unit, List<Post>>,
)