package com.rkfcheung.contact.services

import com.rkfcheung.contact.models.Contact
import com.rkfcheung.contact.repositories.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ContactService {
    @Autowired
    private lateinit var contactRepository: ContactRepository

    fun view(id: Long): Contact? {
        val found = contactRepository.findById(id)
        if (found.isEmpty)
            return null
        return found.get()
    }

    fun search(userId: Long) = contactRepository.findByUserId(userId)

    fun search(userId: Long, firstName: String) = contactRepository.findByUserIdAndFirstName(userId, firstName)

    fun searchByEmail(userId: Long, email: String) = contactRepository.findByUserIdAndEmail(userId, email).firstOrNull()

    fun create(contact: Contact) = update(contact)

    @Transactional
    fun update(contact: Contact): Contact? {
        val user = contact.user
        val userId = user.id ?: return null
        val found = contactRepository.findByUserIdAndEmail(userId, contact.email)
        if (contact.id == null)
            contact.id = found.minBy { it.id ?: 0L }?.id
        contact.id?.let { id ->
            found.filterNot { it.id == id }.forEach { contactRepository.delete(it) }
        }

        return contactRepository.save(contact)
    }

    fun delete(id: Long): Boolean {
        view(id) ?: return false
        contactRepository.deleteById(id)

        return true
    }
}