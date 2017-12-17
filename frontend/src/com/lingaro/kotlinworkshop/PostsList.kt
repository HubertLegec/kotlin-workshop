package com.lingaro.kotlinworkshop

import kotlinx.html.*
import kotlinx.html.js.*
import com.lingaro.kotlinworkshop.model.Post
import react.*
import react.dom.*

class PostsList : ReactDOMComponent<PostsList.Props, ReactComponentNoState>() {
    companion object : ReactComponentSpec<PostsList, Props, ReactComponentNoState>

    init {
        state = ReactComponentNoState()
    }

    override fun ReactDOMBuilder.render() {
        fun postElem(p: Post) {
            a(href = "#", classes = "list-group-item list-group-item-action flex-column align-items-start") {
                div("d-flex w-100 justify-content-between") {
                    h5("mb-1") {
                        +p.author
                    }
                    small("text-muted") {
                        +p.date
                    }
                }
                p("mb-1") {
                    +p.text
                }
            }
        }

        div("container list-group") {
                if (props.posts.isEmpty()) {
                    a(href = "#", classes = "list-group-item list-group-item-action flex-column align-items-start") {
                        div("d-flex w-100 justify-content-between") {
                            h5("mb-1") {
                                +"There are no posts yet"
                            }
                        }
                    }
                } else {
                    for (p in props.posts) {
                        postElem(p)
                    }
                }
        }
    }

    class Props(var posts: List<Post>, var show: (Post) -> Unit = {}) : RProps()
}