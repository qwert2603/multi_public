package com.qwert2603.multi_public.common.data

import com.qwert2603.multi_public.common.Config
import com.qwert2603.multi_public.common.domain.Post
import com.qwert2603.multi_public.common.domain.PostComment
import com.qwert2603.multi_public.common.util.CallResult
import com.qwert2603.multi_public.common.util.callForResult
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class PostsService(
    private val postsMapper: PostsMapper,
    private val commentsMapper: CommentsMapper,
) {

    private val httpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Logging) {
            level = LogLevel.BODY
            logger = Logger.SIMPLE
        }
    }

    suspend fun getPosts(): CallResult<List<Post>> = callForResult {
        httpClient
            .get<PostsResponse>("https://api.vk.com/method/wall.get") {
                parameter("v", "5.131")
                parameter("owner_id", Config.OWNER_ID)
                parameter("offset", 0)
                parameter("count", 100)
                parameter("access_token", Config.ACCESS_TOKEN)
            }
            .let(postsMapper::mapPostsList)
    }

    suspend fun getComments(postId: Long): CallResult<List<PostComment>> = callForResult {
        httpClient
            .get<CommentsResponse>("https://api.vk.com/method/wall.getComments") {
                parameter("v", "5.131")
                parameter("owner_id", Config.OWNER_ID)
                parameter("post_id", postId)
                parameter("offset", 0)
                parameter("count", 100)
                parameter("extended", 1)
                parameter("need_likes", 1)
                parameter("thread_items_count", 10)
                parameter("access_token", Config.ACCESS_TOKEN)
            }
            .let(commentsMapper::mapCommentsList)
    }
}