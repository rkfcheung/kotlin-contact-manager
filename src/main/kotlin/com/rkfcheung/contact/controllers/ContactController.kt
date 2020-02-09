package com.rkfcheung.contact.controllers

import com.rkfcheung.contact.forms.ContactForm
import com.rkfcheung.contact.forms.UserForm
import com.rkfcheung.contact.models.Contact
import com.rkfcheung.contact.models.User
import com.rkfcheung.contact.services.ContactService
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
class ContactController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var contactService: ContactService

    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/contact")
    fun showForm(contactForm: ContactForm): String {
        return "contact"
    }

    @PostMapping("/contact")
    fun saveContact(contactForm: @Valid ContactForm, bindingResult: BindingResult): String {
        //log.info(contactForm.toString())
        //log.info(bindingResult.toString())
        var user = userService.list().singleOrNull()
        if (user == null) {
            UserForm("username", "password").toUser()?.let {
                user = userService.create(it)
            }
        }

        contactForm.toContact(user)?.let {
            contactService.create(it)
        }

        return "redirect:/contact"
    }
}