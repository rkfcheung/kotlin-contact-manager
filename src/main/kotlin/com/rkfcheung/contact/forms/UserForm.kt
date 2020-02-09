package com.rkfcheung.contact.forms

import com.rkfcheung.contact.models.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserForm (var username: @NotNull @Size(min = 4, max = 32) String? = null,
                     var password: @NotNull @Size(min = 4, max = 32) String? = null) {
    private var passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    fun toUser(): User? {
        username?.let { usr ->
            password?.let { pwd ->
                return User(usr, passwordEncoder.encode(pwd))
            }
        }
        return null
    }
}