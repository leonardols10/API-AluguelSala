package com.aluguelsala.events


import com.aluguelsala.service.RentService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateNfeListener(
  private val rentService: RentService
) {
  @Async
  @EventListener
  fun listener(rentEvent: RentEvent){
    val nfe = UUID.randomUUID().toString()
    val rentModel = rentEvent.rentModel.copy(nfe = nfe)

    rentService.update(rentModel)
  }

}