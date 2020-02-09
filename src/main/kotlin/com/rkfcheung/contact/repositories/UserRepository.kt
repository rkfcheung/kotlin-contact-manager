package com.rkfcheung.contact.repositories

import com.rkfcheung.contact.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}