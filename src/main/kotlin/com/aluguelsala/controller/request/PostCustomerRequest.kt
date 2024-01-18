package com.aluguelsala.controller.request

import com.aluguelsala.model.CustomerModel
import com.aluguelsala.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest (
  @field:NotEmpty
  var name: String,
  @field:Email
  @EmailAvailable
  var email: String,

  @field:NotEmpty
  val password: String

)
