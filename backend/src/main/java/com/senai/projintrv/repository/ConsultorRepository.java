package com.senai.projintrv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.projintrv.models.Consultor;

public interface ConsultorRepository extends JpaRepository<Consultor, Long> {

    Optional<Consultor> findByEmail(String email);

}