package com.lingaro.kotlinworkshop.posts

import java.time.format.DateTimeFormatter

class PostDTO (post: Post) {
    var id: Long? = post.id
    var text: String = post.text
    var date: String = post.creationDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:MM:ss"))
    var author: String = post.author.login
}