package com.rkfcheung.contact.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Contact(@ManyToOne var user: User,
                   var firstName: String,
                   var photoUrl: String,
                   var email: String,
                   var phone: String,
                   @Id @GeneratedValue var id: Long? = null) {
}