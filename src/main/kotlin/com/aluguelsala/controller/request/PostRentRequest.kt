package com.aluguelsala.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PostRentRequest (
  @field:NotNull
  @field:Positive
  @JsonAlias("customer_id")
  val customerId: Int,

  @field:NotNull
  @JsonAlias("room_ids")
  val roomIds: Set<Int>
)
