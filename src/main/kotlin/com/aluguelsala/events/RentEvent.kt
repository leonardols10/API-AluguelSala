package com.aluguelsala.events

import com.aluguelsala.model.RentModel
import org.springframework.context.ApplicationEvent

class RentEvent (
   source: Any,
  val rentModel: RentModel
): ApplicationEvent(source)