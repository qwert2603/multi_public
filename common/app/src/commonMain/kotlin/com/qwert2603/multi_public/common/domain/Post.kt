package com.qwert2603.multi_public.common.domain

import java.time.ZonedDateTime

data class Post(
    val id: Long,
    val text: String,
    val added: ZonedDateTime,
    val likesCount: Int,
    val repostsCount: Int,
    val commentsCount: Int,
    val viewsCount: Int,
    val attachments: List<Attachment>,
    val postUrl: String,
) {
    sealed interface Attachment {
        data class Photo(val url: String) : Attachment
        data class Audio(val artist: String, val title: String) : Attachment
        object Unknown : Attachment
    }
}