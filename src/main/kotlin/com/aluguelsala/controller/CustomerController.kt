package com.aluguelsala.controller

import com.aluguelsala.controller.request.PostCustomerRequest
import com.aluguelsala.controller.request.PutCustomerRequest
import com.aluguelsala.controller.response.CustomerResponse
import com.aluguelsala.extension.toCustomerModel
import com.aluguelsala.extension.toResponse
import com.aluguelsala.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("customer")
class CustomerController(
  val customerService: CustomerService
) {
  private val logger: Logger = LoggerFactory.getLogger(RentController::class.java)

  @GetMapping
  fun getAll(@RequestParam name: String?): List<CustomerResponse>  {
   return customerService.getAll(name).map {it . toResponse()}
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createCustomer(@RequestBody @Valid customer: PostCustomerRequest) {
  customerService.create(customer.toCustomerModel())
    logger.info("Create Customer: {}", customer)

  }

  @GetMapping("/{id}")
  fun getCustomer(@PathVariable id: Int): CustomerResponse  {
  return customerService.findById(id).toResponse()
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun updateCustomer(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest)  {
    val customerSaved = customerService.findById(id)
    customerService.updateCustomer(customer.toCustomerModel(customerSaved))
    logger.info("Update Customer: {}", customer)

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteCustomer(@PathVariable id: Int)  {
  customerService.deleteCustomer(id)
    logger.info("Delete  Customer: {}", id)

  }



}