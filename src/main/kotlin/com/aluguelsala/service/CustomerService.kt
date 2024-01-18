package com.aluguelsala.service

import com.aluguelsala.enums.CustomerStatus
import com.aluguelsala.enums.Errors
import com.aluguelsala.enums.Profile
import com.aluguelsala.exception.NotFoundException
import com.aluguelsala.model.CustomerModel
import com.aluguelsala.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
  val customerRepository: CustomerRepository,
  val roomService: RoomService,
  private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

  fun getAll(name: String?): List<CustomerModel>  {
    name?.let {
      return customerRepository.findByNameContaining(it)
    }
    return customerRepository.findAll().toList()
  }

  fun create( customer: CustomerModel) {
   val customerCopy = customer.copy(
      roles = setOf(Profile.CUSTOMER),
     password = bCryptPasswordEncoder.encode(customer.password)
    )
    customerRepository.save(customerCopy)
  }

  fun findById(id: Int): CustomerModel  {
    return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.AL1101.message.format(id), Errors.AL1101.code) }
  }

  fun updateCustomer(customer: CustomerModel)  {
    if(!customerRepository.existsById(customer.id!!)){
      throw NotFoundException(Errors.AL1002.message.format(customer.id), Errors.AL1002.code)
    }
    customerRepository.save(customer)
  }

  fun deleteCustomer( id: Int)  {
    val customer = findById(id)
    roomService.deleteByCustomer(customer)

    customer.status = CustomerStatus.INATIVO

    customerRepository.save(customer)
  }

  fun emailAvailable(email: String): Boolean {
    return !customerRepository.existsByEmail(email)
  }

  fun findByEmail(email: String): CustomerModel? {
    return customerRepository.findByEmail(email)
  }

}