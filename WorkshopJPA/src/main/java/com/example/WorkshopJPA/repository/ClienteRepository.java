package com.example.WorkshopJPA.repository;

import com.example.WorkshopJPA.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
