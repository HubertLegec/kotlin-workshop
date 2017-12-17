package com.lingaro.kotlinworkshop

import kotlinx.coroutines.experimental.async
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.jetbrains.common.inputValue
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class CreatePost : ReactDOMComponent<CreatePost.Props, CreatePost.State>() {
    companion object : ReactComponentSpec<CreatePost, Props, State>

    init {
        state = State()
    }

    override fun ReactDOMBuilder.render() {
        div(classes = "container") {
            h3 {
                +"New thought"
            }
            form {
                textArea(classes = "form-control") {
                    placeholder = "Your thought..."

                    onChangeFunction = {
                        setState {
                            text = it.inputValue
                        }
                    }
                }

                state.errorMessage?.let { message ->
                    p(classes = "form-control") { +message }
                }

                button(classes = "btn btn-default") {
                    + "Post"

                    onClickFunction = {
                        it.preventDefault()
                        createPost()
                    }
                }

            }
        }
    }

    private fun createPost() {
        async {
            postPost(state.text)
            props.refresh()
        }.catch { err -> onFailed(err) }
    }

    private fun onFailed(err: Throwable) {
        setState {
            errorMessage = err.message
        }
    }

    class State(var text: String = "", var errorMessage: String? = null) : RState
    class Props(var refresh: () -> Unit = {}) : RProps()
}