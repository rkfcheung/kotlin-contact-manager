package com.rkfcheung.contact.security.config

import com.rkfcheung.contact.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var userService: UserService

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/contact").hasAnyRole()
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/contact")
                .and()
                .logout()
                .logoutSuccessUrl("/")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder())
    }
}