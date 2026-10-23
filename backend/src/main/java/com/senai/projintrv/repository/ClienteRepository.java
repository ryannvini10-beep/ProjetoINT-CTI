package com.senai.projintrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.projintrv.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    
}
