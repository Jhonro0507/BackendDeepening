package com.example.WorkshopJPA.repository;

import com.example.WorkshopJPA.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    @Query("SELECT h " +
            "FROM Habitacion h " +
            "WHERE h NOT IN (SELECT h2 FROM Habitacion h2 JOIN h2.reservas r " +
            "WHERE r.fechaReserva = :fecha) " +
            "AND h.tipo = :tipo")
    List<Habitacion> findHabitacionesDisponiblesPorFechaYTipo(@Param("fecha") LocalDate fecha, @Param("tipo") String tipo);

    @Query("SELECT h " +
            "FROM Habitacion h " +
            "WHERE h NOT IN (SELECT h2 FROM Habitacion h2 JOIN h2.reservas r " +
            "WHERE r.fechaReserva = :fecha) ")
    List<Habitacion> findHabitacionesDisponiblesPorFecha(@Param("fecha") LocalDate fecha);
}
