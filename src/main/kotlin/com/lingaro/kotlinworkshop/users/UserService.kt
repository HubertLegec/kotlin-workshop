package com.lingaro.kotlinworkshop.users

import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun getCurrentUser() = userRepository.findOneByLogin("user 1") ?: throw EntityNotFoundException("Can't find current user")
}