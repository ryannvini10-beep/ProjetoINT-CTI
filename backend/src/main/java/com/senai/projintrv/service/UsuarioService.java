package com.senai.projintrv.service;

import com.senai.projintrv.dto.UsuarioDTO;
import com.senai.projintrv.model.Usuario;
import com.senai.projintrv.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    // CREATE
    public Usuario criar(UsuarioDTO dto) {

        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new RuntimeException("Login já cadastrado.");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());

        return usuarioRepository.save(usuario);
    }


    // READ - listar todos
    public List<Usuario> listarTodos() {

        return usuarioRepository.findAll();
    }


    // READ - buscar por id
    public Usuario buscarPorId(Long id) {

        return usuarioRepository
                .findById(id)
                .orElseThrow(
                    () -> new RuntimeException("Usuário não encontrado.")
                );
    }


    // UPDATE
    public Usuario atualizar(Long id, UsuarioDTO dto) {

        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.getNome());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());

        return usuarioRepository.save(usuario);
    }


    // DELETE
    public void excluir(Long id) {

        Usuario usuario = buscarPorId(id);

        usuarioRepository.delete(usuario);
    }
}