package com.qwert2603.multi_public.common.data

import kotlinx.serialization.Serializable

@Serializable
class CommentsResponse(
    val response: Response?,
) {
    @Serializable
    class Response(
        val items: List<Comment>,
        val profiles: List<Profile>?,
        val groups: List<Group>?,
    )

    @Serializable
    class Comment(
        val id: Long,
        val text: String?,
        val date: Long,
        val from_id: Long?,
        val likes: Count?,
        val thread: Response?,
    )

    @Serializable
    class Count(
        val count: Int?,
    )

    @Serializable
    class Profile(
        val id: Long,
        val first_name: String,
        val last_name: String,
        val photo_100: String,
    )

    @Serializable
    class Group(
        val id: Long,
        val name: String,
        val photo_100: String,
    )
}