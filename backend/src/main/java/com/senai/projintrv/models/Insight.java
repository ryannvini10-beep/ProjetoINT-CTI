package com.senai.projintrv.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "insight")
public class Insight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "gerado_em", nullable = false)
    private LocalDateTime geradoEm;

    // Muitos insights podem pertencer a um cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    // Muitos insights podem pertencer a um contrato
    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    // Construtor vazio
    public Insight() {
    }

    // Construtor completo
    public Insight(
            String tipo,
            String descricao,
            LocalDateTime geradoEm,
            Cliente cliente,
            Contrato contrato) {

        this.tipo = tipo;
        this.descricao = descricao;
        this.geradoEm = geradoEm;
        this.cliente = cliente;
        this.contrato = contrato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getGeradoEm() {
        return geradoEm;
    }

    public void setGeradoEm(LocalDateTime geradoEm) {
        this.geradoEm = geradoEm;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}