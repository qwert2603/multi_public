package com.qwert2603.multi_public.common.presentation.post_comments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwert2603.multi_public.common.domain.PostComment
import com.qwert2603.multi_public.common.presentation.LoadingStateUi
import com.qwert2603.multi_public.common.util.UrlImage

@Composable
fun PostCommentsUi(
    component: PostComments,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comments") },
                navigationIcon = {
                    // todo: fix ripple and offset
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable(onClick = component::onBackClicked),
                    )
                },
            )
        },
        modifier = modifier,
    ) {
        LoadingStateUi(
            loadingState = state.postCommentsLoadingState,
            onRetry = component::onRetryClicked,
            successContent = { postComments ->
                LazyColumn {
                    items(postComments) { postComment ->
                        PostComment(postComments = postComment)
                        if (postComment.thread != null) {
                            Column(modifier = Modifier.padding(start = 24.dp)) {
                                postComment.thread.forEach {
                                    Divider(thickness = 1.dp)
                                    PostComment(postComments = it)
                                }
                            }
                        }
                        Divider(thickness = 2.dp)
                    }
                }
            },
        )
    }
}

@Composable
private fun PostComment(
    postComments: PostComment,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        UrlImage(
            imageUrl = postComments.authorAvatarUrl,
            placeHolder = Icons.Default.AccountCircle,
            modifier = Modifier.size(36.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                postComments.authorName,
                style = MaterialTheme.typography.h6,
                modifier = Modifier,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(postComments.text, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "${postComments.likesCount} like",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.End),
            )
        }
    }
}