package com.lingaro.kotlinworkshop.posts

import java.time.format.DateTimeFormatter

class PostDTO (post: Post) {
    var id: Long? = post.id
    var text: String = post.text
    var date: String = post.creationDate.format(DateTimeFormatter.ISO_DATE_TIME)
    var author: String = post.author.login
}