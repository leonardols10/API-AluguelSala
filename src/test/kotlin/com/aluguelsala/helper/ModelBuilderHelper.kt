package com.aluguelsala.helper
import com.aluguelsala.enums.CustomerStatus
import com.aluguelsala.enums.Profile
import com.aluguelsala.model.CustomerModel
import com.aluguelsala.model.RentModel
import com.aluguelsala.model.RoomModel
import java.math.BigDecimal
import java.util.*

fun buildCustomer(
  id: Int? = null,
  name: String = "name customer",
  email: String = "${UUID.randomUUID()}@email.com",
  password: String = "password"
) = CustomerModel(
  id = id,
  name = name,
  email = email,
  status = CustomerStatus.ATIVO,
  password = password,
  roles =setOf(Profile.CUSTOMER)
)

fun buildRent(
  id: Int? = null,
  customer: CustomerModel = buildCustomer(),
  rooms: MutableList<RoomModel> =mutableListOf(),
  nfe: String? = UUID.randomUUID().toString(),
  price: BigDecimal = BigDecimal.TEN
) = RentModel (
  id = id,
  customer = customer,
  rooms = rooms,
  nfe =  nfe,
  price = price
)