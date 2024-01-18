package com.aluguelsala.controller

import com.aluguelsala.controller.mapper.RentMapper
import com.aluguelsala.controller.request.PostRentRequest
import com.aluguelsala.service.RentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("rent")
class RentController(
  private val rentService: RentService,
  private val rentMapper: RentMapper
) {
  private val logger: Logger = LoggerFactory.getLogger(RentController::class.java)

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun rent(@RequestBody request: PostRentRequest) {
    logger.info("Received request to rent: {}", request)

    try {
      rentService.create(rentMapper.toModel(request))
      logger.info("Rent created successfully")
    } catch (e: Exception) {
      logger.error("Error creating rent", e)
      throw e
    }
  }

}