package com.lingaro.kotlinworkshop.users

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun findOneByLogin(login: String): User?
}
