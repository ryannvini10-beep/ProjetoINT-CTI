package com.senai.projintrv.dto;

import com.senai.projintrv.models.Perfil;
import com.senai.projintrv.models.Usuario;

// record gerencia as informações que entra e sai da api, sem expor o banco de dados
public record UsuarioResponse(Long id, String nome, String email, Perfil perfil) {
    public static  UsuarioResponse de(Usuario u){
        return new  UsuarioResponse(u.getId(), u.getNome(),u.getEmail(),u.getPerfil());
    }
} 
