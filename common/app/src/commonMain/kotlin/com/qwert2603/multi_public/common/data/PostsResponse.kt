package com.qwert2603.multi_public.common.data

import kotlinx.serialization.Serializable

@Serializable
class PostsResponse(
    val response: Response,
) {
    @Serializable
    class Response(
        val items: List<Post>,
    )

    @Serializable
    class Post(
        val id: Long,
        val text: String?,
        val comments: Count?,
        val likes: Count?,
        val reposts: Count?,
        val views: Count?,
    )

    @Serializable
    class Count(
        val count: Int?,
    )
}