package com.aluguelsala.repository

import com.aluguelsala.enums.RoomStatus
import com.aluguelsala.model.CustomerModel
import com.aluguelsala.model.RoomModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface RoomRepository: JpaRepository<RoomModel,Int> {
  abstract fun findByStatus(status: RoomStatus,pageable: Pageable): Page<RoomModel>

  abstract fun findByCustomer(customer: CustomerModel): List<RoomModel>

//  fun findAll(pageable: Pageable):Page<RoomModel>


}