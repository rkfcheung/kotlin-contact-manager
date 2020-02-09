package com.rkfcheung.contact.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(var username: String,
                var password: String,
                @Id @GeneratedValue var id: Long? = null) {
}