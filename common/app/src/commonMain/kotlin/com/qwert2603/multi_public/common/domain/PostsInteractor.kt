package com.qwert2603.multi_public.common.domain

import com.qwert2603.multi_public.common.data.PostsService
import com.qwert2603.multi_public.util.CallResult

class PostsInteractor(
    private val postsService: PostsService,
) {

    suspend fun getPosts(): CallResult<List<Post>> = postsService.getPosts()

    suspend fun getPostComments(postId: Long): CallResult<List<PostComment>> = postsService.getComments(postId)
}