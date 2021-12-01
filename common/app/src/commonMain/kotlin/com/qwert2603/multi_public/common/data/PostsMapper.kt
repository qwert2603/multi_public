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
        attachments = post.attachments?.mapNotNull(::mapAttachment).orEmpty()
    )

    private fun PostsResponse.Count?.toIntOrZero(): Int = this?.count ?: 0

    private fun mapAttachment(attachment: PostsResponse.Attachment): Post.Attachment? = when (attachment.type) {
        "photo" -> attachment.photo?.let(::mapPhoto)
        else -> {
            println("Unknown attachment.type: ${attachment.type}")
            null
        }
    }

    private fun mapPhoto(photo: PostsResponse.Attachment.Photo): Post.Attachment.Photo? {
        val url = photo.sizes.maxByOrNull { it.width * it.height }?.url ?: return null
        return Post.Attachment.Photo(url = url)
    }
}