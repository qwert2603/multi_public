package com.qwert2603.multi_public.common.data

import com.qwert2603.multi_public.common.domain.PostComment
import com.qwert2603.multi_public.util.ifOrNull

class CommentsMapper {

    fun mapCommentsList(
        commentsResponse: CommentsResponse,
    ): List<PostComment> {
        commentsResponse.response ?: return emptyList()

        val profiles = commentsResponse.response.profiles.orEmpty().associateBy { it.id }
        val groups = commentsResponse.response.groups.orEmpty().associateBy { it.id }

        fun getAuthorName(ownerId: Long): String? =
            profiles[ownerId]?.let { "${it.first_name} ${it.last_name}" }
                ?: groups[-ownerId]?.name

        fun getAuthorAvatarUrl(ownerId: Long): String? =
            profiles[ownerId]?.photo_100
                ?: groups[-ownerId]?.photo_100

        fun mapCommentsList(
            commentsResponse: CommentsResponse.Response,
            isThread: Boolean = false,
        ): List<PostComment> = commentsResponse.items
            .map { comment ->
                PostComment(
                    id = comment.id,
                    text = comment.text.orEmpty(),
                    likesCount = comment.likes?.count ?: 0,
                    authorName = comment.from_id?.let(::getAuthorName).orEmpty(),
                    authorAvatarUrl = comment.from_id?.let(::getAuthorAvatarUrl),
                    thread = ifOrNull(!isThread) {
                        comment.thread
                            ?.let { mapCommentsList(comment.thread, isThread = true) }
                            .orEmpty()
                    },
                )
            }

        return mapCommentsList(
            commentsResponse = commentsResponse.response,
            isThread = false,
        )
    }
}