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
@Table(name = "consultor")
public class Consultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultor")
    private Long idConsultor;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "matricula", length = 50)
    private String matricula;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "senha", nullable = false, length = 150)
    private String senha;

    @Column(name = "telefone", length = 30)
    private String telefone;

    // Um consultor pode ter vários clientes
    @OneToMany(mappedBy = "consultor")
    private List<Cliente> clientes = new ArrayList<>();

    // Construtor vazio
    public Consultor() {
    }

    // Construtor completo
    public Consultor(
            String nome,
            String matricula,
            String email,
            String senha,
            String telefone) {

        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public Long getIdLong() {
        return idConsultor;
    }

    public void setIdConsultor(Long idConsultor) {
        this.idConsultor = idConsultor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}