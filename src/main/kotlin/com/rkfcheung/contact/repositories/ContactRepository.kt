package com.rkfcheung.contact.repositories

import com.rkfcheung.contact.models.Contact
import org.springframework.data.repository.PagingAndSortingRepository

interface ContactRepository: PagingAndSortingRepository<Contact, Long> {
    fun findByUserId(id: Long): List<Contact>
}