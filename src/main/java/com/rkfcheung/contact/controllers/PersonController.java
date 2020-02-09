package com.rkfcheung.contact.controllers;

import com.rkfcheung.contact.forms.PersonForm;
import com.rkfcheung.contact.models.User;
import com.rkfcheung.contact.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PersonController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/person")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/person")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        //log.info(personForm.toString());
        //log.info(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            return "form";
        }

        User user = new User(personForm.getUsername(), passwordEncoder.encode(personForm.getPassword()), null);
        //log.info(user.toString());
        User saved = userService.create(user);
        if (saved != null)
            log.info(saved.toString());

        return "redirect:/contact";
    }
}
