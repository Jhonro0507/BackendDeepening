package com.example.WorkshopJPA.repository;


import com.example.WorkshopJPA.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findByIdReserva(UUID id);
    @Query("SELECT r FROM Reserva r WHERE r.cliente.cedula = :cedula")
    List<Reserva> obtenerReservasPorCedula(@Param("cedula") String cedula);
}
