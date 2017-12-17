package com.lingaro.kotlinworkshop.users

import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
        @Id
        @NotNull
        var login: String = "",
        @NotNull
        var password: String = ""
) {
}