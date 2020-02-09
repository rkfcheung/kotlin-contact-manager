package com.rkfcheung.contact.forms

import com.rkfcheung.contact.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserForm (var username: @NotNull @Size(min = 4, max = 32) String? = null,
                     var password: @NotNull @Size(min = 4, max = 32) String? = null) {
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun toUser(): User? {
        username?.let { usr ->
            password?.let { pwd ->
                return User(usr, passwordEncoder.encode(pwd))
            }
        }
        return null
    }
}