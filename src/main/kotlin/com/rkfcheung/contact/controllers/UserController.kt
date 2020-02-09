package com.rkfcheung.contact.controllers

import com.rkfcheung.contact.forms.UserForm
import com.rkfcheung.contact.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class UserController {
    @Autowired
    private lateinit var userService: UserService

    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/user")
    fun showForm(userForm: UserForm): String {
        return "user"
    }

    @PostMapping("/user")
    fun savePerson(userForm: @Valid UserForm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            log.info(bindingResult.toString())
            return "user"
        }

        userForm.toUser()?.let {
            log.info(it.toString())
            return "redirect:/contact"
        }

        return "user"
    }
}