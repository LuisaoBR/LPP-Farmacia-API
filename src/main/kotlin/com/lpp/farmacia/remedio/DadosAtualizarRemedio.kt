package com.lpp.farmacia.remedio

import jakarta.validation.constraints.NotNull

data class DadosAtualizarRemedio(
        @field:NotNull
        val id: Long,
        val nome: String?,
        val via: Via?,
        val laboratorio: Laboratorio?
)