package com.aluguelsala.extension

import com.aluguelsala.controller.request.PostCustomerRequest
import com.aluguelsala.controller.request.PostRoomRequest
import com.aluguelsala.controller.request.PutCustomerRequest
import com.aluguelsala.controller.request.PutRoomRequest
import com.aluguelsala.controller.response.CustomerResponse
import com.aluguelsala.controller.response.RoomResponse
import com.aluguelsala.enums.CustomerStatus
import com.aluguelsala.enums.RoomStatus
import com.aluguelsala.model.CustomerModel
import com.aluguelsala.model.RoomModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
  return CustomerModel(name = this.name,email = this.email, status = CustomerStatus.ATIVO, password =  this.password)
}
fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
  return CustomerModel(id = previousValue.id,name = this.name,email = this.email, status = previousValue.status, password = previousValue.password)
}

fun PostRoomRequest.toRoomModel(customerModel: CustomerModel): RoomModel{
  return RoomModel(
    name = this.name,
    price = this.price,
    status = RoomStatus.ATIVO,
    customer = customerModel
  )
}

fun PutRoomRequest.toRoomModel(previousValue: RoomModel): RoomModel{
  return RoomModel(
    id = previousValue.id,
    name = this.name ?: previousValue.name,
    price = this.price ?: previousValue.price,
    status = previousValue.status,
    customer =  previousValue.customer
  )
}

fun CustomerModel.toResponse(): CustomerResponse {
  return CustomerResponse(
    id = this.id,
    name = this.name,
    email = this.email,
    status = this.status
  )
}


fun RoomModel.toResponse(): RoomResponse {
  return RoomResponse(
    id = this.id,
    name = this.name,
    price = this.price,
    customer = this.customer,
    status = this.status
  )

}