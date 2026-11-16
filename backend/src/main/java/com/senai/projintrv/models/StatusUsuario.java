package com.senai.projintrv.models;                          // pacote desta classe
// ============================================================================
//  Os estados do usuário.
// PENDENTE (espera liberação), APROVADO (pode entrar) ou BLOQUEADO.
// Usado por: Usuario.
// ============================================================================

/** PENDENTE: cadastrou e espera liberação. APROVADO: pode entrar. BLOQUEADO: acesso negado. */
public enum StatusUsuario { PENDENTE, APROVADO, BLOQUEADO }  // enum: estados da conta, controlados pelo admin
