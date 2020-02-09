package com.rkfcheung.contact.repositories

import com.rkfcheung.contact.models.Contact
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository: PagingAndSortingRepository<Contact, Long> {
    fun findByUserId(userId: Long): List<Contact>

    fun findByUserIdAndFirstName(userId: Long, firstName: String): List<Contact>

    fun findByUserIdAndEmail(userId: Long, email: String): List<Contact>
}
