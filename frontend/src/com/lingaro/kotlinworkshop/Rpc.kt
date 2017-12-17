package com.lingaro.kotlinworkshop

import com.lingaro.kotlinworkshop.model.CreatePostDTO
import kotlinx.coroutines.experimental.await
import com.lingaro.kotlinworkshop.model.Post
import org.w3c.fetch.*
import kotlin.browser.*
import kotlin.js.*

suspend fun getPosts(): List<Post> =
        getAndParseResult("/posts", null, ::parsePostListResponse)

suspend fun postPost(text: String) =
        postAndParseResult("/posts", JSON.stringify(CreatePostDTO(text)), { Unit })

suspend fun deletePost(id: Long) =
        deleteAndParseResult("/posts/$id", null, { Unit })

private fun parsePostListResponse(json: dynamic): List<Post> {
    val elements = json as Array<dynamic>
    return elements.map(::parsePost)
}

private fun parsePost(json: dynamic): Post {
    return Post(json.id, json.author, json.text, json.date)
}

suspend fun <T> postAndParseResult(url: String, body: dynamic, parse: (dynamic) -> T): T =
        requestAndParseResult("POST", url, body, parse)

suspend fun <T> getAndParseResult(url: String, body: dynamic, parse: (dynamic) -> T): T =
        requestAndParseResult("GET", url, body, parse)

suspend fun <T> deleteAndParseResult(url: String, body: dynamic, parse: (dynamic) -> T): T =
        requestAndParseResult("DELETE", url, body, parse)

suspend fun <T> requestAndParseResult(method: String, url: String, body: dynamic, parse: (dynamic) -> T): T {
    val response = window.fetch(url, object: RequestInit {
        override var method: String? = method
        override var body: dynamic = body
        override var credentials: RequestCredentials? = "same-origin".asDynamic()
        override var headers: dynamic = json("Accept" to "application/json", "Content-Type" to "application/json")
    }).await()
    return parse(response.json().await())
}
