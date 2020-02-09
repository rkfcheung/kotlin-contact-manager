package com.rkfcheung.contact.services

import com.rkfcheung.contact.models.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
internal class UserServiceTest {
    @Autowired
    private lateinit var userService: UserService


    @Test
    fun testCreate() {
        val user = User(username = "username", password = "password")
        val created = userService.create(user)
        assertTrue(created?.id ?: 0L > 0L)
        assertEquals(user, created)
    }

    @Test
    fun testGet() {

    }

    @Test
    fun testUpdate() {

    }

    @Test
    fun testDelete() {

    }

    @Test
    fun testList() {
        val users = userService.list()
        users.forEach {
            println(it)
        }
    }
}