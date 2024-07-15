package com.lpp.farmacia.remedio

import java.time.LocalDate

data class DadosDetalhamentoRemedio(
        val id: Long,
        val nome: String,
        val via: Via,
        val lote: String,
        val quantidade: Int,
        val validade: LocalDate,
        val laboratorio: Laboratorio,
        val ativo: Boolean
) {
    constructor(remedio: Remedio) : this(
            remedio.id ?: throw IllegalArgumentException("Remedio id cannot be null"),
            remedio.nome,
            remedio.via,
            remedio.lote,
            remedio.quantidade,
            remedio.validade,
            remedio.laboratorio,
            remedio.ativo
    )
}