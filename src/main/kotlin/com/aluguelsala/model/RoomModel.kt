package com.aluguelsala.model

import com.aluguelsala.enums.Errors
import com.aluguelsala.enums.RoomStatus
import com.aluguelsala.exception.BadRequestException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name="room")
data class RoomModel(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int? = null,

  @Column
  var name: String,

  @Column
  var price: BigDecimal,

  @ManyToOne
  @JoinColumn(name = "customer_id")
  var customer: CustomerModel? = null
){

  @Column
  @Enumerated(EnumType.STRING)
  var status: RoomStatus? = null
    set(value){
      if(field == RoomStatus.CANCELADO || field == RoomStatus.DELETADO){
        throw BadRequestException(Errors.AL1002.message.format(status), Errors.AL1002.code)
      }
      field = value

    }
  constructor(id: Int? = null,
  name: String,
  price: BigDecimal,
  customer: CustomerModel? = null,
  status: RoomStatus?
  ): this(id, name, price, customer){
    this.status = status
  }

  fun isValidName(): Boolean {
    return verifyNameLength(this.name)
  }

  fun verifyNameLength(name: String): Boolean {
    return name.length < 3;
  }


}