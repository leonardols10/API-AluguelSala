package com.aluguelsala.controller.response

import com.aluguelsala.enums.CustomerStatus

data class CustomerResponse (
  var id: Int? = null,

  var name: String,

  var email: String,

  var status: CustomerStatus
)
