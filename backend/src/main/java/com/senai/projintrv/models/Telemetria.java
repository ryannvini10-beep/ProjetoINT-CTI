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
@Table(name = "telemetria")
public class Telemetria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "evento", nullable = false, length = 150)
    private String evento;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Muitas telemetrias pertencem a um serviço
    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;

    // Construtor vazio
    public Telemetria() {
    }

    // Construtor
    public Telemetria(
            String evento,
            String status,
            LocalDateTime timestamp,
            Servico servico) {

        this.evento = evento;
        this.status = status;
        this.timestamp = timestamp;
        this.servico = servico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}