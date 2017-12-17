package com.lingaro.kotlinworkshop

import kotlinx.html.*
import com.lingaro.kotlinworkshop.model.*
import kotlinx.coroutines.experimental.launch
import react.*
import react.dom.*
import kotlin.browser.*

fun main(args: Array<String>) {
    runtime.wrappers.require("app.css")

    ReactDOM.render(document.getElementById("content")) {
        div {
            Application {}
        }
    }
}

class Application : ReactDOMComponent<ReactComponentNoProps, ApplicationPageState>() {
    companion object : ReactComponentSpec<Application, ReactComponentNoProps, ApplicationPageState>

    init {
        state = ApplicationPageState(emptyList())
    }

    private fun loadPosts() {
        launch {
            val p = getPosts()
            setState {
                posts = p
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        loadPosts()
    }


    override fun ReactDOMBuilder.render() {
        div {
            header {
                h2 {
                    +"Kotlin workshop"
                }
            }
            section {
                div("container") {
                    h1 { +"Posts" }
                }
                PostsList {
                    posts = this@Application.state.posts
                }
                CreatePost {
                    refresh = ::loadPosts
                }
            }
            footer {
                +"Lingaro Kotlin workshop by Hubert Legęć"
            }
        }
    }

}


class ApplicationPageState(var posts: List<Post>) : RState

