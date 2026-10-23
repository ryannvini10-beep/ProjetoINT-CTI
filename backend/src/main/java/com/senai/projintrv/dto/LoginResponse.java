package com.senai.projintrv.dto;

public record LoginResponse(String token, String tipo, UsuarioResponse usuario) {
} 
