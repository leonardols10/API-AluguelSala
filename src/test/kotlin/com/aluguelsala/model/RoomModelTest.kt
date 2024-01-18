package com.aluguelsala.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

 class RoomModelTest{
   @Test
   @DisplayName("""
        Dado que um nome menor que 3 caracteres é validado
        Quando a funcao for chamada
        Entao devera ser retornado false
    """)
   fun isValidName() {
     val invalidName = "IA"
     val bookModel = RoomModel(1, invalidName, BigDecimal.ZERO, null)
     val retorno = bookModel.isValidName()
     Assertions.assertTrue(retorno)
   }

 }