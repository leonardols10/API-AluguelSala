package com.aluguelsala

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class AluguelSalaApplication
fun main(args: Array<String>) {
	runApplication<AluguelSalaApplication>(*args)
}
