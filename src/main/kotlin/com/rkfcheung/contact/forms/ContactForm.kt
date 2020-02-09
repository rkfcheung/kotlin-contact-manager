package com.rkfcheung.contact.forms

import com.rkfcheung.contact.models.Contact
import com.rkfcheung.contact.models.User
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ContactForm (var firstName: @NotNull @Size(min = 4, max = 32) String? = null,
                        var photoUrl: @NotNull @URL @Size(max = 255) String? = null,
                        var email: @NotNull @Email String? = null,
                        var phone: @NotNull @Size(min = 4, max = 32) String? = null) {
    fun toContact(user: User?): Contact? {
        if (user == null)
            return null
        firstName?.let { fn ->
            photoUrl?.let { pl ->
                email?.let { em ->
                    phone?.let { ph ->
                        return Contact(user, fn, pl, em, ph)
                    }
                }
            }

        }
        return null
    }
}