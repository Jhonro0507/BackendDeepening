package com.example.WorkshopJPA.model;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reservas")
public class Reserva {

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Id
    private UUID idReserva;

    @Column(name = "total_pagar")
    private long totalPagar;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reserva_habitacion",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "habitacion_id"),
            uniqueConstraints = {
                    @UniqueConstraint(name = "unique_reserva_habitacion", columnNames = {"reserva_id", "habitacion_id"})
            }
    )
    private List<Habitacion> habitaciones = new ArrayList<>();
}
