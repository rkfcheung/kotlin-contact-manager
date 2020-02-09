package com.rkfcheung.contact.repositories

import com.rkfcheung.contact.models.Contact
import com.rkfcheung.contact.models.User
import org.springframework.data.repository.PagingAndSortingRepository

interface ContactRepository: PagingAndSortingRepository<Contact, Long> {
    fun findByUser(user: User): List<Contact>
}