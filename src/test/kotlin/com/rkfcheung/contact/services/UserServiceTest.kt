package com.rkfcheung.contact.services

import com.rkfcheung.contact.models.Contact
import com.rkfcheung.contact.models.User
import com.rkfcheung.contact.repositories.ContactRepository
import com.rkfcheung.contact.repositories.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
internal class UserServiceTest {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var contactRepository: ContactRepository

    private val userCount = 8
    private val users = mutableListOf<User>()

    @BeforeEach
    fun setUp() {
        contactRepository.deleteAll()
        userRepository.deleteAll()

        for (i in 1..userCount) {
            users += userRepository.save(User("username$i", "password$i"))
        }

        val user1 = users.first()
        contactRepository.save(Contact(user1, "username2", "", "email@example.com", "1234"))
    }

    @Test
    fun testCreate() {
        val user = User("username", "password")
        val created = userService.create(user)
        val found = userService.get(user.id ?: 0L)
        assertTrue(created?.id ?: 0L > 0L)
        assertEquals(user, created)
        assertEquals(user, found)

        assertNull(userService.create(user))
    }

    @Test
    fun testGet() {
        val id = users.last().id ?: 0L
        val user8 = userService.get(id)
        assertEquals(id, user8?.id)

        assertNull(userService.get(id * 2))
    }

    @Test
    fun testUpdate() {
        userService.list().forEach { println(it) }

        val id = users.last().id ?: 0L - 1L
        val pwd7 = "hello"
        assertTrue(userService.update(id, pwd7))

        val user7 = userService.get(id)
        assertEquals(pwd7, user7?.password)
    }

    @Test
    fun testDelete() {
        val id6 = users.last().id ?: 0L - 2L
        assertTrue(userService.delete(id6))
        assertFalse(userService.delete(id6))

        val id1 = users.first().id ?: 0L
        assertFalse(userService.delete(id1))
        contactRepository.deleteAll()
        assertTrue(userService.delete(id1))
    }

    @Test
    fun testList() {
        val users = userService.list()
        var i = 0
        users.forEach {
            assertNotNull(it.id)
            i++
        }
        assertEquals(userCount, i)
    }
}