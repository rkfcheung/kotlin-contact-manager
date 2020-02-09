package com.rkfcheung.contact.services

import com.rkfcheung.contact.models.Contact
import com.rkfcheung.contact.models.User
import com.rkfcheung.contact.repositories.ContactRepository
import com.rkfcheung.contact.repositories.UserRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ContactServiceTest {
    @Autowired
    private lateinit var contactService: ContactService

    @Autowired
    private lateinit var contactRepository: ContactRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    private val contactCount = 8
    private val contacts = mutableListOf<Contact>()

    @BeforeEach
    fun setUp() {
        contactRepository.deleteAll()
        userRepository.deleteAll()

        val user1 = userRepository.save(User("user1", "pwd1"))
        for (i in 1..contactCount) {
            contacts += contactRepository.save(Contact(user1, "firstName$i", "img$i.jpg", "email$i@example.com", "1234$i"))
        }
    }

    @Test
    fun testView() {
        val contact = contacts.first()
        val id = contact.id ?: 0L
        val found = contactService.view(id)
        assertNotNull(found)
        assertEquals(id, found?.id)
        assertEquals(contact, found)

        assertNull(contactService.view(-1))
    }

    @Test
    fun testSearch() {
    }

    @Test
    fun testCreate() {
    }

    @Test
    fun testUpdate() {
    }

    @Test
    fun testDelete() {
    }
}