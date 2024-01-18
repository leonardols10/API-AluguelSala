package com.aluguelsala.events


import com.aluguelsala.service.RentService
import com.aluguelsala.service.RoomService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateRentRoomListener(
  private val roomService: RoomService
) {
  @EventListener
  @Async
  fun listener(rentEvent: RentEvent){

    roomService.rent(rentEvent.rentModel.rooms)
  }

}