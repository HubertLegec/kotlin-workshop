package com.lingaro.kotlinworkshop.posts

class CreatePostDTO() {
    lateinit var text: String

    constructor(text: String): this() {
        this.text = text
    }
}