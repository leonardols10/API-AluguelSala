package com.aluguelsala.enums

enum class Errors(val code: String, val message: String) {
  AL0001("AL0001","Invalid request"),
  AL1001(code = "AL-1001", message = "Room [%s] not exists"),
  AL1002(code = "AL-1002", message = "Cannot update room with status [%s]"),
  AL1101(code = "AL-1101", message = "Customer [%s] not exists")
}