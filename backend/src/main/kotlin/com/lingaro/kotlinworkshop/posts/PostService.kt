package com.lingaro.kotlinworkshop.posts

import com.lingaro.kotlinworkshop.users.UserService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(
        private val postRepository: PostRepository,
        private val userService: UserService
) {
    fun getAllPosts() = postRepository.findAll()
            .map { PostDTO(it) }

    fun savePost(text: String): Long? {
        val post = Post(text, LocalDateTime.now(), userService.getCurrentUser())
        return postRepository.save(post).id
    }

    fun deletePost(id: Long) = postRepository.delete(id)
}