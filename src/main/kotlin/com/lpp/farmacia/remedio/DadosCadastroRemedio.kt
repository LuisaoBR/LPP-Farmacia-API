package com.lpp.farmacia.remedio

import jakarta.persistence.Enumerated
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import jakarta.validation.constraints.NotNull

data class DadosCadastroRemedio(
        @field:NotBlank
        val nome: String,

        @field:Enumerated
        val via: Via,

        @field:NotBlank
        val lote: String,

        val quantidade: Int,

        @field:Future
        val validade: LocalDate,

        @field:Enumerated
        val laboratorio: Laboratorio
)