package com.aluguelsala.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name="rent")

data class RentModel(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int? = null,

  @ManyToOne
  @JoinColumn(name = "customer_id")
  val customer: CustomerModel,
  @ManyToMany
  @JoinTable(name= "rent_room",
  joinColumns = [JoinColumn(name ="rent_id")],
  inverseJoinColumns = [JoinColumn(name="room_id")])
  val rooms: MutableList<RoomModel>,

  @Column
  val nfe: String? = null,

  @Column
  val price: BigDecimal,

  @Column(name = "created_at")
  val createdAt: LocalDateTime = LocalDateTime.now()


)