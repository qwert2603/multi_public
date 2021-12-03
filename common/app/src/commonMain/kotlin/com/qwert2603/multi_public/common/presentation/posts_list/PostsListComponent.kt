package com.qwert2603.multi_public.common.presentation.posts_list

import com.arkivanov.decompose.ComponentContext
import com.qwert2603.multi_public.common.domain.PostsInteractor
import com.qwert2603.multi_public.common.util.UrlLauncher
import com.qwert2603.multi_public.common.util.createComponentScope
import com.qwert2603.multi_public.util.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsListComponent(
    componentContext: ComponentContext,
    private val postsInteractor: PostsInteractor,
    private val urlLauncher: UrlLauncher,
    private val onPostSelected: (id: Long) -> Unit,
) : PostsList, ComponentContext by componentContext {

    private val componentScope = createComponentScope()

    private var loadPostsJob by switchJob()

    override val state = MutableStateFlow(
        PostsListState(
            postsListLoadingState = LoadingState.None,
        )
    )

    private fun updateState(update: (PostsListState) -> PostsListState) {
        state.update(update)
    }

    init {
        loadPostsList()
    }

    private fun loadPostsList() {
        loadPostsJob = componentScope.launch {
            updateState { it.copy(postsListLoadingState = LoadingState.Loading) }
            val postsList = when (val result = postsInteractor.getPosts()) {
                is CallResult.Error -> LoadingState.Error()
                is CallResult.Success -> LoadingState.Success(result.data)
            }
            updateState { it.copy(postsListLoadingState = postsList) }
        }
    }

    override fun onRefresh() {
        loadPostsList()
    }

    override fun onRetryClicked() {
        if (state.value.postsListLoadingState.isError) {
            loadPostsList()
        }
    }

    override fun onPostClicked(id: Long) {
        onPostSelected(id)
    }

    override fun onOpenPostWebClicked(id: Long) {
        val post = state.value.postsListLoadingState
            .dataOrNull
            ?.find { it.id == id }
            ?: return
        urlLauncher.openUrl(post.postUrl)
    }

    override fun onOpenLinkClicked(url: String) {
        urlLauncher.openUrl(url)
    }
}