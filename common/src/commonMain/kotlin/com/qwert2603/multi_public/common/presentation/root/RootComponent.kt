package com.qwert2603.multi_public.common.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.qwert2603.multi_public.common.presentation.post_comments.PostComments
import com.qwert2603.multi_public.common.presentation.post_comments.PostCommentsComponent
import com.qwert2603.multi_public.common.presentation.posts_list.PostsList
import com.qwert2603.multi_public.common.presentation.posts_list.PostsListComponent
import org.koin.core.Koin

class RootComponent(
    componentContext: ComponentContext,
    private val koin: Koin,
) : Root, ComponentContext by componentContext {

    private val router = router<Config, Root.Child>(
        initialConfiguration = Config.PostsList,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    override val routerState: Value<RouterState<*, Root.Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): Root.Child = when (config) {
        is Config.PostsList -> Root.Child.List(itemList(componentContext))
        is Config.PostComments -> Root.Child.Comments(itemDetails(componentContext, config))
    }

    private fun itemList(componentContext: ComponentContext): PostsList =
        PostsListComponent(
            componentContext = componentContext,
            postsInteractor = koin.get(),
            onPostSelected = { router.push(Config.PostComments(postId = it)) }
        )

    private fun itemDetails(componentContext: ComponentContext, config: Config.PostComments): PostComments =
        PostCommentsComponent(
            componentContext = componentContext,
            postId = config.postId,
            onBackClicked = router::pop,
            postsInteractor = koin.get(),
        )

    private sealed class Config : Parcelable {
        @Parcelize
        object PostsList : Config()

        @Parcelize
        data class PostComments(val postId: Long) : Config()
    }
}