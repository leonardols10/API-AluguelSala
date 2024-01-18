package com.aluguelsala.service

import com.aluguelsala.events.RentEvent
import com.aluguelsala.helper.buildRent
import com.aluguelsala.repository.RentRepository
import org.junit.jupiter.api.Assertions.*
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher

@ExtendWith(MockKExtension::class)

class RentServiceTest(){
  @MockK
  private lateinit var rentRepository: RentRepository

  @MockK
  private lateinit var applicationEventPublisher: ApplicationEventPublisher

  @InjectMockKs
  private lateinit var rentService: RentService

  val purchaseEventSlot =slot<RentEvent>()

  @Test
  fun `should create a event`() {
    val purchase = buildRent()

    every{rentRepository.save(purchase)}returns purchase
    every{applicationEventPublisher.publishEvent(any())} just runs

    rentService.create(purchase)

    verify(exactly = 1){rentRepository.save(purchase)}
    verify(exactly = 1){applicationEventPublisher.publishEvent(capture(purchaseEventSlot))}

    assertEquals(purchase, purchaseEventSlot.captured.rentModel)
  }

  @Test
  fun `must update rent`() {
    val purchase = buildRent()

    every{rentRepository.save(purchase)}returns purchase

    rentService.update(purchase)

    verify(exactly = 1){rentRepository.save(purchase)}
  }


}