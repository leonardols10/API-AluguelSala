package com.aluguelsala.exception
import com.aluguelsala.controller.response.ErrorResponse
import com.aluguelsala.controller.response.FieldErrorResponse
import com.aluguelsala.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import  org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException

@ControllerAdvice
class ControllerAdvice {
  @ExceptionHandler(NotFoundException::class)
  fun handleNotFoundException(ex: NotFoundException, request: WebRequest):ResponseEntity<ErrorResponse>{
   val erro =  ErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      ex.message,
      ex.errorCode,
      erros = null
    )
    return ResponseEntity(erro,HttpStatus.NOT_FOUND)
  }


  @ExceptionHandler(BadRequestException::class)
  fun handleBadRequestException(ex: BadRequestException, request: WebRequest):ResponseEntity<ErrorResponse>{
    val erro =  ErrorResponse(
      HttpStatus.BAD_REQUEST.value(),
      ex.message,
      ex.errorCode,
      erros = null
    )
    return ResponseEntity(erro,HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest):ResponseEntity<ErrorResponse>{
    val erro =  ErrorResponse(
      HttpStatus.UNPROCESSABLE_ENTITY.value(),
      ex.message,
      Errors.AL0001.code,
      ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage?: "Invalid",it.field) }
    )
    return ResponseEntity(erro,HttpStatus.BAD_REQUEST)
  }

}