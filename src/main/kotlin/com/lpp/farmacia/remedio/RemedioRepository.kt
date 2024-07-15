package com.lpp.farmacia.remedio

import org.springframework.data.jpa.repository.JpaRepository

interface RemedioRepository : JpaRepository<Remedio, Long> {
    fun findAllByAtivoTrue(): List<Remedio>
}