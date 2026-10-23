package com.senai.projintrv.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    // Muitos clientes pertencem a um consultor
    @ManyToOne
    @JoinColumn(name = "id_consultor", nullable = false)
    private Consultor consultor;

    // Código CTI
    @Column(name = "codigo_cti", length = 50)
    private String codigoCTI;

    @Column(name = "nome_empresa", nullable = false, length = 180)
    private String nomeEmpresa;

    @Column(name = "segmento", nullable = false, length = 100)
    private String segmento;

    @Column(name = "faturamento_anual", precision = 15, scale = 2)
    private BigDecimal faturamentoAnual;

    // Faixa de faturamento
    @Column(name = "faixa_faturamento", length = 100)
    private String faixaFaturamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelCliente nivel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusCliente status;

    // Um cliente pode ter vários contratos
    @OneToMany(mappedBy = "cliente")
    private List<Contrato> contratos = new ArrayList<>();

    // Um cliente pode ter vários insights
    @OneToMany(mappedBy = "cliente")
    private List<Insight> insights = new ArrayList<>();

    // Construtor vazio
    public Cliente() {
    }

    // Construtor
    public Cliente(
            Consultor consultor,
            String codigoCTI,
            String nomeEmpresa,
            String segmento,
            BigDecimal faturamentoAnual,
            String faixaFaturamento,
            NivelCliente nivel,
            StatusCliente status) {

        this.consultor = consultor;
        this.codigoCTI = codigoCTI;
        this.nomeEmpresa = nomeEmpresa;
        this.segmento = segmento;
        this.faturamentoAnual = faturamentoAnual;
        this.faixaFaturamento = faixaFaturamento;
        this.nivel = nivel;
        this.status = status;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Consultor getConsultor() {
        return consultor;
    }

    public void setConsultor(Consultor consultor) {
        this.consultor = consultor;
    }

    public String getCodigoCTI() {
        return codigoCTI;
    }

    public void setCodigoCTI(String codigoCTI) {
        this.codigoCTI = codigoCTI;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public BigDecimal getFaturamentoAnual() {
        return faturamentoAnual;
    }

    public void setFaturamentoAnual(BigDecimal faturamentoAnual) {
        this.faturamentoAnual = faturamentoAnual;
    }

    public String getFaixaFaturamento() {
        return faixaFaturamento;
    }

    public void setFaixaFaturamento(String faixaFaturamento) {
        this.faixaFaturamento = faixaFaturamento;
    }

    public NivelCliente getNivel() {
        return nivel;
    }

    public void setNivel(NivelCliente nivel) {
        this.nivel = nivel;
    }

    public StatusCliente getStatus() {
        return status;
    }

    public void setStatus(StatusCliente status) {
        this.status = status;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Insight> getInsights() {
        return insights;
    }

    public void setInsights(List<Insight> insights) {
        this.insights = insights;
    }
}