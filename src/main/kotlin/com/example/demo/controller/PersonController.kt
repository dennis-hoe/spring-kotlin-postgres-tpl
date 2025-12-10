package com.example.demo.controller

import com.example.demo.model.Person
import com.example.demo.repository.PersonRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/persons")
class PersonController(private val repo: PersonRepository) {

    @GetMapping
    fun all(): List<Person> = repo.findAll()

    @PostMapping
    fun create(@RequestBody person: Person): ResponseEntity<Person> {
        val saved = repo.save(person)
        return ResponseEntity.ok(saved)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Person> =
        repo.findById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())
}
