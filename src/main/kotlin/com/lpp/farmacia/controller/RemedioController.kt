package com.lpp.farmacia.controller

import com.lpp.farmacia.remedio.*
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/remedios")
class RemedioController(private val repository: RemedioRepository) {

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody dados: DadosCadastroRemedio?, uriBuilder: UriComponentsBuilder): ResponseEntity<DadosDetalhamentoRemedio> {
        val remedio = Remedio(dados!!)
        repository.save(remedio)
        val uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.id).toUri()
        return ResponseEntity.created(uri).body(DadosDetalhamentoRemedio(remedio))
    }

    @GetMapping
    fun listar(): ResponseEntity<List<DadosListagemRemedio>> {
        val lista = repository.findAllByAtivoTrue().map { DadosListagemRemedio(it) }
        return ResponseEntity.ok(lista)
    }

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, @RequestBody dados: DadosAtualizarRemedio): ResponseEntity<DadosDetalhamentoRemedio> {
        val remedio = repository.findById(id).orElseThrow { NoSuchElementException("Remédio não encontrado") }
        remedio.atualizarInformacoes(dados)
        return ResponseEntity.ok(DadosDetalhamentoRemedio(remedio))
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun excluir(@PathVariable id: Long): ResponseEntity<Void> {
        repository.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    fun inativar(@PathVariable id: Long): ResponseEntity<Void> {
        val remedio = repository.findById(id).orElseThrow { NoSuchElementException("Remédio não encontrado") }
        remedio.inativar()
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    fun reativar(@PathVariable id: Long): ResponseEntity<Void> {
        val remedio = repository.findById(id).orElseThrow { NoSuchElementException("Remédio não encontrado") }
        remedio.setAtivo()
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun detalhar(@PathVariable id: Long): ResponseEntity<DadosDetalhamentoRemedio> {
        val remedio = repository.findById(id).orElseThrow { NoSuchElementException("Remédio não encontrado") }
        return ResponseEntity.ok(DadosDetalhamentoRemedio(remedio))
    }
}