package com.qwert2603.multi_public.common.presentation.posts_list

import com.arkivanov.decompose.ComponentContext
import com.qwert2603.multi_public.common.domain.PostsInteractor
import com.qwert2603.multi_public.common.util.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsListComponent(
    componentContext: ComponentContext,
    private val postsInteractor: PostsInteractor,
    private val onPostSelected: (id: Long) -> Unit,
) : PostsList, ComponentContext by componentContext {

    private val componentScope = createComponentScope()

    override val state = MutableStateFlow(
        PostsListState(
            postsListLoadingState = LoadingState.None,
        )
    )

    init {
        loadPostsList()
    }

    private fun loadPostsList() {
        componentScope.launch {
            state.update { it.copy(postsListLoadingState = LoadingState.Loading) }
            val postsList = when (val result = postsInteractor.getPosts()) {
                is CallResult.Error -> LoadingState.Error()
                is CallResult.Success -> LoadingState.Success(result.data)
            }
            state.update { it.copy(postsListLoadingState = postsList) }
        }
    }

    override fun onRetryClicked() {
        if (state.value.postsListLoadingState.isError) {
            loadPostsList()
        }
    }

    override fun onPostClicked(id: Long) {
        onPostSelected(id)
    }
}