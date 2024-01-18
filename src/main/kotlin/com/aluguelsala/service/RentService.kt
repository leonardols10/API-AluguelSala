package com.aluguelsala.service

import com.aluguelsala.events.RentEvent
import com.aluguelsala.model.RentModel
import com.aluguelsala.repository.RentRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class RentService(
  private val rentRepository: RentRepository,
  private val applicationEventPublisher: ApplicationEventPublisher
) {

  fun create( RentModel: RentModel){
    rentRepository.save(RentModel)

    applicationEventPublisher.publishEvent(RentEvent(this, RentModel ))
  }

  fun update(rentModel: RentModel) {
    rentRepository.save(rentModel)
  }

}
