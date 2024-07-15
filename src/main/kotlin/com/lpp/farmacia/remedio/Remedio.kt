package com.lpp.farmacia.remedio

import java.time.LocalDate
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "remedios")
@Table(name = "Remedio")
data class Remedio(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var nome: String,

        @Enumerated(EnumType.STRING)
        var via: Via,
        var lote: String,
        var quantidade: Int = 0,
        var validade: LocalDate,

        @Enumerated(EnumType.STRING)
        var laboratorio: Laboratorio,
        var ativo: Boolean = true
) {
    constructor(dados: DadosCadastroRemedio) : this(
            null, // id inicializado como null
            dados.nome,
            dados.via,
            dados.lote,
            dados.quantidade,
            dados.validade,
            dados.laboratorio,
            true // ativo inicializado como true
    )

    fun atualizarInformacoes(dados: DadosAtualizarRemedio) {
        dados.nome?.let { this.nome = it }
        dados.via?.let { this.via = it }
        dados.laboratorio?.let { this.laboratorio = it }
    }

    fun inativar() {
        this.ativo = false
    }

    fun setAtivo() {
        this.ativo = true
    }
}