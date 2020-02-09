package com.rkfcheung.contact.controllers

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.web.server.LocalServerPort
import java.util.concurrent.TimeUnit

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
internal class UserControllerTest {

    @LocalServerPort
    private var port: Int? = null

    private var browser: HtmlUnitDriver? = null

//    @BeforeEach
//    fun setUp() {
//        browser = HtmlUnitDriver()
//        browser?.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
//    }
//
//    @AfterEach
//    fun tearDown() {
//        browser?.quit()
//    }
//
//    @Test
//    fun testRegisterForm() {
//        browser?.get(homePageUrl())
//    }
//
//    @Test
//    fun testRegister() {
//    }
//
//    private fun homePageUrl(): String? {
//        return "http://localhost:$port/"
//    }
}