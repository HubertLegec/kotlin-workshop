package com.lingaro.kotlinworkshop.posts

import com.lingaro.kotlinworkshop.users.User
import org.hibernate.validator.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Post(
        @NotBlank
        var text: String,
        @NotNull
        var creationDate: LocalDateTime,
        @ManyToOne
        @NotNull
        var author: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}