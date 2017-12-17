package com.lingaro.kotlinworkshop.posts

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>