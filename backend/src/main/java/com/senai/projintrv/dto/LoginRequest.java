package com.senai.projintrv.dto;
import  jakarta.validation.constraints.NotBlank;
// DTO com as  credencias enviadas pelo cliente
public record LoginRequest(@NotBlank String email, @NotBlank String Senha) {}
