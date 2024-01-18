package com.aluguelsala.controller.response

import com.aluguelsala.enums.RoomStatus
import com.aluguelsala.model.CustomerModel
import java.math.BigDecimal

data class RoomResponse (
  var id: Int? = null,

  var name: String,

  var price: BigDecimal,

  var customer: CustomerModel? = null,

  var status: RoomStatus? = null

)
