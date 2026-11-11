package com.senai.projintrv.controller;

import com.senai.projintrv.dto.UsuarioDTO;
import com.senai.projintrv.model.Usuario;
import com.senai.projintrv.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    // CREATE
    @PostMapping
    public ResponseEntity<Usuario> criar(
            @RequestBody UsuarioDTO dto) {

        Usuario usuario = usuarioService.criar(dto);

        return ResponseEntity.ok(usuario);
    }


    // READ - listar todos
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {

        return ResponseEntity.ok(
                usuarioService.listarTodos()
        );
    }


    // READ - buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.buscarPorId(id)
        );
    }


    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioDTO dto) {

        return ResponseEntity.ok(
                usuarioService.atualizar(id, dto)
        );
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        usuarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}