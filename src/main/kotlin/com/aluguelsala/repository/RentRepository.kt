package com.aluguelsala.repository

import com.aluguelsala.model.RentModel
import org.springframework.data.repository.CrudRepository

interface RentRepository:CrudRepository<RentModel, Int> {


}
