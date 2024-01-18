package com.aluguelsala.service

import com.aluguelsala.enums.CustomerStatus
import com.aluguelsala.enums.Errors
import com.aluguelsala.enums.Profile
import com.aluguelsala.exception.NotFoundException
import com.aluguelsala.helper.buildCustomer
import com.aluguelsala.model.CustomerModel
import com.aluguelsala.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import org.junit.jupiter.api.assertThrows
import io.mockk.runs


@ExtendWith(MockKExtension::class)
 class CustomerServiceTest{
    @MockK
    private lateinit var  customerRepository: CustomerRepository
  @MockK

  private lateinit var  roomService: RoomService
  @MockK

  private lateinit var  bCryptPasswordEncoder: BCryptPasswordEncoder

  @InjectMockKs
  @SpyK
   private lateinit var customerService: CustomerService

   @Test
   fun `Should return all customers`(){

     val name = Math.random().toString()
     val fakeCustomer = listOf(buildCustomer(), buildCustomer())

     every { customerRepository.findByNameContaining(name) } returns fakeCustomer

     val customers = customerService.getAll(name)

     assertEquals(fakeCustomer, customers)
     verify(exactly = 0) { customerRepository.findAll() }
     verify(exactly = 1) { customerRepository.findByNameContaining(name) }

   }

  @Test
  fun `must return customers when name is provided` () {
    val name = Math.random().toString()
    val fakeCustomer = listOf(buildCustomer(), buildCustomer())

    every { customerRepository.findByNameContaining(name) } returns fakeCustomer

    val customers = customerService.getAll(name)

    assertEquals(fakeCustomer, customers)
    verify(exactly = 0) { customerRepository.findAll() }
    verify(exactly = 1) { customerRepository.findByNameContaining(name) }
  }

  @Test
  fun `must create customer and encrypt password`() {
    val initialPassword = Random().nextInt().toString()
    val fakeCustomer = buildCustomer(password = initialPassword)
    val fakePassword = UUID.randomUUID().toString()
    val fakeCustomerEncrypted = fakeCustomer.copy(password = fakePassword)

    every { customerRepository.save(fakeCustomerEncrypted) } returns fakeCustomer
    every { bCryptPasswordEncoder.encode(initialPassword) } returns fakePassword

    customerService.create(fakeCustomer)

    verify(exactly = 1) { customerRepository.save(fakeCustomerEncrypted) }
    verify(exactly = 1) { bCryptPasswordEncoder.encode(initialPassword) }
  }

  @Test
  fun `must return customer by id`() {
    val id = Random().nextInt()
    val fakeCustomer = buildCustomer(id = id)

    every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)

    val customer = customerService.findById(id)

    assertEquals(fakeCustomer, customer)
    verify(exactly = 1) { customerRepository.findById(id) }
  }

  @Test
  fun `should indicate an error when the customer is not found by id`() {
    val id = Random().nextInt()

    every { customerRepository.findById(id) } returns Optional.empty()

    val error = assertThrows<NotFoundException>{
      customerService.findById(id)
    }

    assertEquals("Customer [${id}] not exists!", error.message)
    assertEquals("ML-201", error.errorCode)

    verify(exactly = 1) { customerRepository.findById(id) }
  }

  @Test
  fun `deve atualizar customer`() {
    val id = Random().nextInt()
    val fakeCustomer = buildCustomer(id = id)

    every { customerRepository.existsById(id) } returns true
    every { customerRepository.save(fakeCustomer) } returns fakeCustomer

    customerService.updateCustomer(fakeCustomer)

    verify(exactly = 1) { customerRepository.existsById(id) }
    verify(exactly = 1) { customerRepository.save(fakeCustomer) }
  }

  @Test
  fun `should indicate an error when the customer is not updated`() {
    val id = Random().nextInt()
    val fakeCustomer = buildCustomer(id = id)

    every { customerRepository.existsById(id) } returns false
    every { customerRepository.save(fakeCustomer) } returns fakeCustomer

    val error = assertThrows<NotFoundException>{
      customerService.updateCustomer(fakeCustomer)
    }

    assertEquals("Customer [${id}] not exists!", error.message)
    assertEquals("ML-201", error.errorCode)

    verify(exactly = 1) { customerRepository.existsById(id) }
    verify(exactly = 0) { customerRepository.save(any()) }
  }

  @Test
  fun `must delete customer`() {
    val id = Random().nextInt()
    val fakeCustomer = buildCustomer(id = id)
    val expectedCustomer = fakeCustomer.copy(status = CustomerStatus.INATIVO)

    every { customerService.findById(id) } returns fakeCustomer
    every { customerRepository.save(expectedCustomer) } returns expectedCustomer
    every { roomService.deleteByCustomer(fakeCustomer) } just runs

    customerService.deleteCustomer(id)

    verify(exactly = 1) { customerService.findById(id) }
    verify(exactly = 1) { roomService.deleteByCustomer(fakeCustomer) }
    verify(exactly = 1) { customerRepository.save(expectedCustomer) }
  }

  @Test
  fun `It should indicate an error if you cannot delete customer`() {
    val id = Random().nextInt()

    every { customerService.findById(id) } throws NotFoundException(Errors.AL1101.message.format(id), Errors.AL1101.code)

    val error = assertThrows<NotFoundException>{
      customerService.deleteCustomer(id)
    }

    assertEquals("Customer [${id}] not exists!", error.message)
    assertEquals("ML-201", error.errorCode)

    verify(exactly = 1) { customerService.findById(id) }
    verify(exactly = 0) { roomService.deleteByCustomer(any()) }
    verify(exactly = 0) { customerRepository.save(any()) }
  }


  @Test
  fun `must return true when the email is accessible`() {
    val email = "${Random().nextInt()}@email.com"

    every { customerRepository.existsByEmail(email) } returns false

    val emailAvailable = customerService.emailAvailable(email)

    assertTrue(emailAvailable)
    verify(exactly = 1) { customerRepository.existsByEmail(email) }
  }







 }