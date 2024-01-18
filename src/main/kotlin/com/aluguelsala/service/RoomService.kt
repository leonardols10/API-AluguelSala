package com.aluguelsala.service

import com.aluguelsala.enums.Errors
import com.aluguelsala.enums.RoomStatus
import com.aluguelsala.exception.NotFoundException
import com.aluguelsala.model.CustomerModel
import com.aluguelsala.model.RoomModel
import com.aluguelsala.repository.RoomRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RoomService(
  val roomRepository: RoomRepository
) {
  fun create(room: RoomModel) {
    roomRepository.save(room)
  }

  fun finAll(pageable: Pageable): Page<RoomModel> {
    return roomRepository.findAll(pageable)
  }

  fun findActives(pageable: Pageable): Page<RoomModel> {
    return roomRepository.findByStatus(RoomStatus.ATIVO, pageable)
  }

  fun findById(id: Int): RoomModel {
    return roomRepository.findById(id).orElseThrow{NotFoundException(Errors.AL1001.message.format(id), Errors.AL1001.code)}
  }

  fun delete(id: Int) {
    val room = findById(id)

    room.status = RoomStatus.CANCELADO

    update(room)

  }

  fun update(room: RoomModel) {
     roomRepository.save(room)
  }

  fun deleteByCustomer(customer: CustomerModel) {

    val rooms = roomRepository.findByCustomer(customer)

    for(room in rooms){
      room.status = RoomStatus.DELETADO
    }

    roomRepository.saveAll(rooms)
  }

  fun findAllByIds(roomIds: Set<Int>): List<RoomModel> {
    return roomRepository.findAllById(roomIds).toList()
  }

  fun rent(rooms: MutableList<RoomModel>) {
    rooms.map {
      it.status = RoomStatus.ALUGADO
    }
    roomRepository.saveAll(rooms)
  }


}
