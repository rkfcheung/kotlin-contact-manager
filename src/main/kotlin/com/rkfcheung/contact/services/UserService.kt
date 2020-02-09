package com.rkfcheung.contact.services

import com.rkfcheung.contact.models.User
import com.rkfcheung.contact.models.UserPrincipal
import com.rkfcheung.contact.repositories.ContactRepository
import com.rkfcheung.contact.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var contactRepository: ContactRepository

    fun create(user: User): User? {
        if (userRepository.findByUsername(user.username) != null)
            return null
        return userRepository.save(user)
    }

    fun get(id: Long): User? {
        val found = userRepository.findById(id)
        if (found.isEmpty)
            return null
        return found.get()
    }

    fun update(id: Long, password: String): Boolean {
        val user = get(id) ?: return false
        user.password = password
        userRepository.save(user)
        return true
    }

    fun delete(id: Long): Boolean {
        val found = get(id) ?: return false
        if (contactRepository.findByUserId(id).isNotEmpty())
            return false
        userRepository.delete(found)
        return true
    }

    fun list(): Iterable<User> {
        return userRepository.findAll()
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User '$username' not found")
        return UserPrincipal(user)
    }
}