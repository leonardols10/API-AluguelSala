package com.aluguelsala.repository

import com.aluguelsala.helper.buildCustomer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)

class CustomerRespositoryTest {
  @Autowired
  private lateinit var customerRepository: CustomerRepository

  @BeforeEach
  fun setup() = customerRepository.deleteAll()

  @Test
  fun `must return name containing`() {
    val carol = customerRepository.save(buildCustomer(name = "Carol"))//insere registros
    val carola = customerRepository.save(buildCustomer(name = "Carola"))//insere registros
    customerRepository.save(buildCustomer(name = "Ana"))

    val customers = customerRepository.findByNameContaining("Ca")

    assertEquals(listOf(carol, carola), customers)
  }

  @Nested
  inner class `exists by email` {
    @Test
    fun `must return true when the email exists`() {
      val email = "email@teste.com"

      customerRepository.save(buildCustomer(email = email))

      val exists = customerRepository.existsByEmail(email)

      assertTrue(exists)
    }

    @Test
    fun `should return false when the email does not exist`() {
      val email = "emailnaoexiste@teste.com"

      val exists = customerRepository.existsByEmail(email)

      assertFalse(exists)
    }
  }

  @Nested
  inner class `find by email` {
    @Test
    fun `should return customer when the email exists`() {
      val email = "email@teste.com"

      val customer = customerRepository.save(buildCustomer(email = email))

      val result = customerRepository.findByEmail(email)

      assertNotNull(result) //indica que o result não é nulo
      assertEquals(customer, result)
    }

    @Test
    fun `must return null when the email does not exist`() {
      val email = "emailnaoexiste@teste.com"

      val result = customerRepository.findByEmail(email)

      assertNull(result)
    }
  }
}