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
        val owner_id: Long,
        val text: String?,
        val date: Long,
        val comments: Count?,
        val likes: Count?,
        val reposts: Count?,
        val views: Count?,
        val attachments: List<Attachment>?,
    )

    @Serializable
    class Count(
        val count: Int?,
    )

    @Serializable
    class Attachment(
        val type: String,
        val photo: Photo?,
    ) {
        @Serializable
        class Photo(
            val sizes: List<Size>,
        )

        @Serializable
        class Size(
            val url: String,
            val width: Int,
            val height: Int,
        )
    }
}