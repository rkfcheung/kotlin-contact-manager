package com.rkfcheung.contact.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Contact(@Id @GeneratedValue var id: Long?,
                   var userId: Long) {
}