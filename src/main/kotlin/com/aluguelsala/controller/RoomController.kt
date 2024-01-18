package com.aluguelsala.controller

import com.aluguelsala.controller.request.PostRoomRequest
import com.aluguelsala.controller.request.PutRoomRequest
import com.aluguelsala.controller.response.RoomResponse
import com.aluguelsala.extension.toResponse
import com.aluguelsala.extension.toRoomModel
import com.aluguelsala.service.CustomerService
import com.aluguelsala.service.RoomService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("room")

class RoomController(
  val customerService: CustomerService,
  val roomService: RoomService
) {
  private val logger: Logger = LoggerFactory.getLogger(RoomController::class.java)

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(@RequestBody @Valid request: PostRoomRequest){
    val customer = customerService.findById(request.customerId)
    roomService.create(request.toRoomModel(customer))
    logger.info("Room created successfully: $request")
  }

  @GetMapping
  fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<RoomResponse> {
    return roomService.finAll(pageable).map {it.toResponse()}
  }

  @GetMapping("/active")
  fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<RoomResponse>{
    return roomService.findActives(pageable).map {it.toResponse()}
  }

  @GetMapping("/{id}")
  fun findById(@PathVariable id: Int): RoomResponse{
    return roomService.findById(id).toResponse()
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(@PathVariable id: Int) {
    roomService.delete(id)
    logger.info("Room deleted successfully with ID: $id")

  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun update(@PathVariable id: Int, @RequestBody  room: PutRoomRequest) {
    val roomSaved = roomService.findById(id)
    roomService.update(room.toRoomModel(roomSaved))
    logger.info("Room updated successfully with ID: $id")

  }


}