package com.rkfcheung.contact.services

import com.rkfcheung.contact.models.Contact
import com.rkfcheung.contact.repositories.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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

    fun create(contact: Contact) = contactRepository.save(contact)

    fun update(contact: Contact) = contactRepository.save(contact)

    fun delete(id: Long) = contactRepository.deleteById(id)
}