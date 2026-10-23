package com.senai.projintrv.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.projintrv.models.Consultor;
import com.senai.projintrv.service.ConsultorService;

@RestController
@RequestMapping("/consultores")
public class ConsultorController {

    private final ConsultorService service;

    public ConsultorController(ConsultorService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Consultor criar(@RequestBody Consultor consultor) {
        return service.criar(consultor);
    }

    // LOGIN
    @PostMapping("/login")
    public Consultor login(@RequestBody Consultor consultor) {
        return service.login(
                consultor.getEmail(),
                consultor.getSenha()
        );
    }

    // READ - TODOS
    @GetMapping
    public List<Consultor> listar() {
        return service.listar();
    }

    // READ - POR ID
    @GetMapping("/{id}")
    public Consultor buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Consultor atualizar(
            @PathVariable Long id,
            @RequestBody Consultor consultor) {

        return service.atualizar(id, consultor);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}