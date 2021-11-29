package com.qwert2603.multi_public.common.data

import com.qwert2603.multi_public.common.domain.Post

class PostsMapper {

    fun mapPostsList(postsResponse: PostsResponse): List<Post> = postsResponse
        .response
        .items
        .map(::mapPost)

    private fun mapPost(post: PostsResponse.Post) = Post(
        id = post.id,
        text = post.text.orEmpty(),
        likesCount = post.likes.toIntOrZero(),
        repostsCount = post.reposts.toIntOrZero(),
        commentsCount = post.comments.toIntOrZero(),
        viewsCount = post.views.toIntOrZero(),
    )

    private fun PostsResponse.Count?.toIntOrZero(): Int = this?.count ?: 0
}