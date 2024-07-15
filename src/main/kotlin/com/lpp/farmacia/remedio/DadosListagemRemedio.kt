package com.lpp.farmacia.remedio

import java.time.LocalDate

data class DadosListagemRemedio(
        val id: Long,
        val nome: String,
        val via: Via,
        val lote: String,
        val laboratorio: Laboratorio,
        val validade: LocalDate
) {
    constructor(remedio: Remedio) : this(
            remedio.id ?: throw IllegalArgumentException("Remedio id cannot be null"),
            remedio.nome,
            remedio.via,
            remedio.lote,
            remedio.laboratorio,
            remedio.validade
    )
}
