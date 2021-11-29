package com.qwert2603.multi_public.common.presentation.post_comments

import com.arkivanov.decompose.ComponentContext
import com.qwert2603.multi_public.common.domain.PostsInteractor
import com.qwert2603.multi_public.common.util.createComponentScope
import com.qwert2603.multi_public.util.CallResult
import com.qwert2603.multi_public.util.Error
import com.qwert2603.multi_public.util.LoadingState
import com.qwert2603.multi_public.util.isError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostCommentsComponent(
    componentContext: ComponentContext,
    private val postId: Long,
    private val onBackClicked: () -> Unit,
    private val postsInteractor: PostsInteractor,
) : PostComments, ComponentContext by componentContext {

    private val componentScope = createComponentScope()

    override val state = MutableStateFlow(
        PostCommentsState(
            postCommentsLoadingState = LoadingState.None,
        )
    )

    init {
        loadPostComments()
    }

    private fun loadPostComments() {
        componentScope.launch {
            state.update { it.copy(postCommentsLoadingState = LoadingState.Loading) }
            val postComments = when (val result = postsInteractor.getPostComments(postId)) {
                is CallResult.Error -> LoadingState.Error()
                is CallResult.Success -> LoadingState.Success(result.data)
            }
            state.update { it.copy(postCommentsLoadingState = postComments) }
        }
    }

    override fun onRetryClicked() {
        if (state.value.postCommentsLoadingState.isError) {
            loadPostComments()
        }
    }

    override fun onBackClicked() {
        onBackClicked.invoke()
    }
}