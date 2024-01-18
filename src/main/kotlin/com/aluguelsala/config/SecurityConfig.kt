package com.aluguelsala.config

import com.aluguelsala.repository.CustomerRepository
import com.aluguelsala.security.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
class SecurityConfig(
  private val customerRepository: CustomerRepository
) {

  @Bean
  fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Bean
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    return http
      .cors {}
      .csrf { it.disable() }
      .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
      .authorizeHttpRequests { auth ->
        auth.requestMatchers(HttpMethod.DELETE).authenticated()
        auth.anyRequest().permitAll()
      }.build()
  }




}
