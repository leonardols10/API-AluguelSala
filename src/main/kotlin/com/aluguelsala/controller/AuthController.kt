package com.aluguelsala.controller

import com.aluguelsala.controller.request.LoginRequest
import com.aluguelsala.controller.response.Message
import com.aluguelsala.service.CustomerService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.Claims
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping("login")

class AuthController(private val customerService: CustomerService) {
  var  secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)

  @PostMapping("customer")
  fun login( @RequestBody body:LoginRequest, response: HttpServletResponse): ResponseEntity<Any>{
    val customer = this.customerService.findByEmail(body.email)

    if (customer == null) {
      return ResponseEntity.badRequest().body(Message("Customer Not found"))
    }
    if(!customer.comparePassword(body.password)){
      return ResponseEntity.badRequest().body(Message("Invalid login"))
    }

    val issuer = customer.id.toString()

    val jwt = Jwts.builder()
      .setIssuer(issuer)
      .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact()

    val cookie = Cookie("jwt", jwt)
    cookie.isHttpOnly = true

    response.addCookie(cookie)


    return ResponseEntity.ok(Message("sucess"))
  }


  @PostMapping("logout")
  fun logout(response: HttpServletResponse): ResponseEntity<Any> {
    val cookie = Cookie("jwt", "")
    cookie.maxAge = 0

    response.addCookie(cookie)

    return ResponseEntity.ok(Message("success"))
  }





}