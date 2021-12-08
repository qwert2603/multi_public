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
            "video" -> attachment.video?.let(::mapVideo)
            "audio" -> attachment.audio?.let(::mapAudio)
            "link" -> attachment.link?.let(::mapLink)
            "poll" -> attachment.poll?.let(::mapPoll)
            else -> {
                println("Unknown attachment.type: ${attachment.type}")
                null
            }
        }
        return result ?: Post.Attachment.Unknown
    }

    private fun List<PostsResponse.Attachment.Size>.getMaxSizeUrl() = this.maxByOrNull { it.width * it.height }?.url

    private fun mapPhoto(photo: PostsResponse.Attachment.Photo): Post.Attachment.Photo? {
        val url = photo.sizes.getMaxSizeUrl() ?: return null
        return Post.Attachment.Photo(url = url)
    }

    private fun mapVideo(video: PostsResponse.Attachment.Video) = Post.Attachment.Video(
        title = video.title.orEmpty(),
        imageUrl = video.image?.getMaxSizeUrl() ?: video.first_frame?.getMaxSizeUrl(),
    )

    private fun mapAudio(audio: PostsResponse.Attachment.Audio) = Post.Attachment.Audio(
        artist = audio.artist,
        title = audio.title,
    )

    private fun mapLink(link: PostsResponse.Attachment.Link) = Post.Attachment.Link(
        url = link.url,
        title = link.title,
        photoUrl = link.photo?.let(::mapPhoto)?.url,
    )

    private fun mapPoll(poll: PostsResponse.Attachment.Poll) = Post.Attachment.Poll(
        question = poll.question,
        votes = poll.votes,
        answers = poll.answers.map {
            Post.Attachment.Poll.Answer(
                text = it.text,
                votes = it.votes,
            )
        },
    )
}