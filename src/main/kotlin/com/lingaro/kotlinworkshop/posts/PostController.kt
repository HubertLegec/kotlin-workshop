package com.lingaro.kotlinworkshop.posts

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("posts")
class PostController(
        private val postService: PostService
) {
    @GetMapping
    fun getAllPosts() = postService.getAllPosts()

    @PostMapping
    fun createPost(@RequestBody post: CreatePostDTO) = postService.savePost(post.text)

    @DeleteMapping(value = "/{id}")
    fun deletePost(@PathVariable id: Long) = postService.deletePost(id)
}