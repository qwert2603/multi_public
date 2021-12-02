package com.qwert2603.multi_public.common.data

object VkUtil {
    fun postUrl(ownerId: Long, postId: Long) = "https://vk.com/wall${ownerId}_${postId}"
}