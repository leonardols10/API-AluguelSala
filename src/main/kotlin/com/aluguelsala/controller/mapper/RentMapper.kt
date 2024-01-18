package com.aluguelsala.controller.mapper

import com.aluguelsala.controller.request.PostRentRequest
import com.aluguelsala.model.RentModel
import com.aluguelsala.service.CustomerService
import com.aluguelsala.service.RoomService
import org.springframework.stereotype.Component

@Component
class RentMapper(
  private val roomService: RoomService,
  private val customerService: CustomerService

) {
  fun toModel(request: PostRentRequest): RentModel{
    val customer = customerService.findById(request.customerId)

    val rooms = roomService.findAllByIds(request.roomIds)

    return RentModel(
      customer = customer,
      rooms = rooms.toMutableList(),
      price = rooms.sumOf { it.price }

    )

  }
}