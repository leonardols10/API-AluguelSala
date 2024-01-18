package com.aluguelsala.controller.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PutRoomRequest (
  var name: String?,
  var price: BigDecimal?
)
