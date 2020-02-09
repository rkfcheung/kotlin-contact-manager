package com.rkfcheung.contact.services

import com.rkfcheung.contact.models.User
import com.rkfcheung.contact.repositories.ContactRepository
import com.rkfcheung.contact.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var contactRepository: ContactRepository

    fun create(user: User): User? {
        if (userRepository.findByUsername(user.username) != null)
            return null
        return userRepository.save(user)
    }

    fun get(id: Long) = userRepository.findById(id)

    fun update(id: Long, user: User): User? {
        if (get(id).isEmpty)
            return null
        user.id = id
        return userRepository.save(user)
    }

    fun delete(id: Long): Boolean {
        val saved= get(id)
        if (saved.isEmpty)
            return false
        if (contactRepository.findByUserId(id).isNotEmpty())
            return false
        userRepository.delete(saved.get())
        return true
    }

    fun list(): Iterable<User> {
        return userRepository.findAll()
    }
}