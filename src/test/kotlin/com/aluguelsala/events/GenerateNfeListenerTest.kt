package com.aluguelsala.events

import com.aluguelsala.helper.buildRent
import com.aluguelsala.service.RentService
import org.junit.jupiter.api.Assertions.*
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)

internal class GenerateNfeListenerTest{
  @MockK
  private lateinit var rentService: RentService

  @InjectMockKs
  private lateinit var generateNfeListener: GenerateNfeListener

  @Test
  fun `should generate nfe`() {
    val purchase = buildRent(nfe = null)
    val fakeNfe = UUID.randomUUID()
    val purchaseExpected = purchase.copy(nfe = fakeNfe.toString())
    mockkStatic(UUID::class)

    every { UUID.randomUUID() } returns fakeNfe
    every { rentService.update(purchaseExpected) } just runs

    generateNfeListener.listener(RentEvent(this, purchase))

    verify(exactly = 1) { rentService.update(purchaseExpected) }
  }

}