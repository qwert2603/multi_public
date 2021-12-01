package com.qwert2603.multi_public.common.domain

import java.time.ZonedDateTime

data class PostComment(
    val id: Long,
    val text: String,
    val added: ZonedDateTime,
    val likesCount: Int,
    val authorName: String,
    val authorAvatarUrl: String?,
    val thread: List<PostComment>?,
) {
    init {
        if (thread != null) {
            check(thread.all { it.thread == null }) { "Nested thread are NOT supported!" }
        }
    }
}