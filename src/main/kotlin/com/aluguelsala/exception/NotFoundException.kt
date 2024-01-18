package com.aluguelsala.exception

class NotFoundException( override val message: String, val errorCode: String):Exception() {
}