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
        added = ServiceMapperUtil.parseZonedDateTime(post.date),
        likesCount = post.likes.toIntOrZero(),
        repostsCount = post.reposts.toIntOrZero(),
        commentsCount = post.comments.toIntOrZero(),
        viewsCount = post.views.toIntOrZero(),
        attachments = post.attachments?.map(::mapAttachment).orEmpty(),
        postUrl = VkUtil.postUrl(ownerId = post.owner_id, postId = post.id),
    )

    private fun PostsResponse.Count?.toIntOrZero(): Int = this?.count ?: 0

    private fun mapAttachment(attachment: PostsResponse.Attachment): Post.Attachment {
        val result = when (attachment.type) {
            "photo" -> attachment.photo?.let(::mapPhoto)
            "audio" -> attachment.audio?.let(::mapAudio)
            else -> {
                println("Unknown attachment.type: ${attachment.type}")
                null
            }
        }
        return result ?: Post.Attachment.Unknown
    }

    private fun mapPhoto(photo: PostsResponse.Attachment.Photo): Post.Attachment.Photo? {
        val url = photo.sizes.maxByOrNull { it.width * it.height }?.url ?: return null
        return Post.Attachment.Photo(url = url)
    }

    private fun mapAudio(audio: PostsResponse.Attachment.Audio) = Post.Attachment.Audio(
        artist = audio.artist,
        title = audio.title,
    )
}