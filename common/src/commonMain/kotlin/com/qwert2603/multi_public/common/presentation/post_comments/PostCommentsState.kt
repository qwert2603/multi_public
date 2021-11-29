package com.qwert2603.multi_public.common.presentation.post_comments

import com.qwert2603.multi_public.common.domain.PostComment
import com.qwert2603.multi_public.util.LoadingState

data class PostCommentsState(
    val postCommentsLoadingState: LoadingState<Unit, List<PostComment>>,
)