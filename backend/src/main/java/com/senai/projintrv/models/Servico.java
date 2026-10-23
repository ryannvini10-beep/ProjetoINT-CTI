package com.senai.projintrv.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "categoria", length = 100)
    private String categoria;

    // Um serviço pode ter vários contratos
    @OneToMany(mappedBy = "servico")
    private List<Contrato> contratos = new ArrayList<>();

    // Um serviço pode ter várias telemetrias
    @OneToMany(mappedBy = "servico")
    private List<Telemetria> telemetrias = new ArrayList<>();

    // Construtor vazio
    public Servico() {
    }

    // Construtor
    public Servico(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Telemetria> getTelemetrias() {
        return telemetrias;
    }

    public void setTelemetrias(List<Telemetria> telemetrias) {
        this.telemetrias = telemetrias;
    }
}