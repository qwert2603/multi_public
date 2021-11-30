package com.qwert2603.multi_public.common.presentation.posts_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwert2603.multi_public.about.AboutUi
import com.qwert2603.multi_public.common.domain.Post
import com.qwert2603.multi_public.design.components.LoadingStateUi

@Composable
fun PostsListUi(
    component: PostsList,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Posts list") },
                actions = {
                    AboutUi() // todo: remove
                },
            )
        },
        modifier = modifier,
    ) {
        LoadingStateUi(
            loadingState = state.postsListLoadingState,
            onRetry = component::onRetryClicked,
            successContent = { postsList ->
                LazyColumn {
                    items(postsList) { post ->
                        PostItem(post = post, onClick = { component.onPostClicked(post.id) })
                        Divider()
                    }
                }
            },
        )
    }
}

@Composable
fun PostItem(
    post: Post,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            post.text,
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.caption,
            ) {
                Text("${post.likesCount} like")
                Text("${post.repostsCount} repost")
                Text("${post.commentsCount} comment")
                Text("${post.viewsCount} view")
            }
        }
    }
}