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
        val contact = contacts.last()
        val userId = contact.user.id ?: 0L
        val found = contactService.search(userId)
        assertEquals(contacts, found)
        assertTrue(contactService.search(-1).isEmpty())

        val found2 = contactService.search(userId, contact.firstName).first()
        assertEquals(contact, found2)
        assertTrue(contactService.search(userId, "notFound").isEmpty())
        assertTrue(contactService.search(-1, contact.firstName).isEmpty())

        val found3 = contactService.searchByEmail(userId, contact.email)
        assertEquals(contact, found3)
    }

    @Test
    fun testCreate() {
        val user = contacts.first().user
        val contact = Contact(user, "firstName", "photoUrl.jpg", "email@example.com", "0987")
        val created = contactService.create(contact)
        val id = created?.id ?: 0L
        assertTrue(id > 0L)
        assertEquals(contact, created)

        val created2 = contactService.create(contact)
        val id2 = created2?.id ?: 0L
        assertEquals(id, id2)
    }

    @Test
    fun testUpdate() {
        val contact = contacts.first()
        val contact2 = contacts.last()
        val id = contact.id
        val user = contact.user
        val contactToBeUpdated = Contact(user, contact.firstName, contact.photoUrl, contact.email, contact.phone)
        val updated = contactService.update(contactToBeUpdated)
        contactService.search(user.id ?: 0L).forEach { println(it) }
        assertEquals(id, updated?.id)

        contactToBeUpdated.email = contact2.email
        val updated2 = contactService.update(contactToBeUpdated)
        contactService.search(user.id ?: 0L).forEach { println(it) }

        assertEquals(id, updated2?.id)
    }

    @Test
    fun testDelete() {
        val id = contacts.first().id ?: 0L
        assertTrue(contactService.delete(id))
        assertFalse(contactService.delete(-1))
    }
}