package com.qwert2603.multi_public.common.presentation.posts_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwert2603.multi_public.common.domain.Post
import com.qwert2603.multi_public.design.components.LoadingStateUi
import com.qwert2603.multi_public.design.components.UrlImage
import com.qwert2603.multi_public.design.components.VerticalScrollbar
import com.qwert2603.multi_public.design.components.verticalScrollbarWidth
import com.qwert2603.multi_public.util.DateTimeUtil
import com.qwert2603.multi_public.util.allCases

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
                    IconButton(
                        onClick = component::onRefresh,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "refresh",
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) {
        LoadingStateUi(
            loadingState = state.postsListLoadingState,
            onRetry = component::onRetryClicked,
            successContent = { postsList ->
                Box {
                    val lazyListState = rememberLazyListState()

                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.padding(end = verticalScrollbarWidth),
                    ) {
                        items(postsList) { post ->
                            PostItem(
                                post = post,
                                onClick = { component.onPostClicked(post.id) },
                                onOpenWebClicked = { component.onOpenWebClicked(post.id) },
                            )
                            Divider()
                        }
                    }

                    VerticalScrollbar(
                        lazyListState = lazyListState,
                        modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    )
                }
            },
        )
    }
}

@Composable
fun PostItem(
    post: Post,
    onClick: () -> Unit,
    onOpenWebClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                DateTimeUtil.formatDateTime(post.added),
                style = MaterialTheme.typography.body2,
            )

            IconButton(onClick = onOpenWebClicked) {
                Icon(
                    imageVector = Icons.Default.MoreVert, // todo: better icon
                    contentDescription = "open in browser",
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            post.text,
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.height(12.dp))
        post.attachments.forEach {
            when (it) {
                is Post.Attachment.Photo -> UrlImage(
                    imageUrl = it.url,
                    placeHolder = Icons.Default.ArrowDropDown,
                    modifier = Modifier.fillMaxWidth().height(280.dp),
                )
            }.allCases
            Spacer(modifier = Modifier.height(12.dp))
        }
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