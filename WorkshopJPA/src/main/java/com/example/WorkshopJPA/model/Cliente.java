package com.example.WorkshopJPA.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clientes")

public class Cliente {

    @NotNull
    @Column
    private String nombre;

    @NotNull
    @Column
    private String apellido;

    @Pattern(regexp = "\\d+", message = "La cédula debe ser numérica")
    @Column(unique = true)
    private String cedula;

    @Column
    private String direccion;

    @Min(0)
    @Column
    private int edad;

    @Email
    @Column
    private String correoElectronico;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idCliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Reserva> reservas = new ArrayList<>();

}