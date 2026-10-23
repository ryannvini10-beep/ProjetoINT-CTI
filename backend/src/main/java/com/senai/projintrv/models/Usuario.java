package com.senai.projintrv.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated; // Com asterisco ele importa todas as dependencias
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity // Relaciona esta classe a uma entidade persistente
@Table(name = "usuarios",uniqueConstraints = @UniqueConstraint (columnNames = "email"))
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identificador gerado pelo banco
    private  Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 160)
    private  String email;
    @Column (nullable = false)
    private  String senhaHash; // Nunca armazenar a senha original

    @Enumerated (EnumType.STRING) // Guarda User/Admin como texto no PostegreSQL
    @Column (nullable = false, length = 20)
    private  Perfil perfil = Perfil.USER;

    // Cria o construtor
    public Usuario(){} // Exigido pelo JPA

    public Long getId(){return id;}
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getSenhaHash(){return  senhaHash;}
    public void setSenhaHash(String senhaHash){this.senhaHash = senhaHash;}
    public Perfil getPerfil(){return  perfil;}
    public  void setPerfil(Perfil perfil){this.perfil = perfil;}




}
