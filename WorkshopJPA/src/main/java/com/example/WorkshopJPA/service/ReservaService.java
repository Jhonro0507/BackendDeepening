package com.example.WorkshopJPA.service;

import com.example.WorkshopJPA.DTO.ReservaDTO;
import com.example.WorkshopJPA.model.Cliente;
import com.example.WorkshopJPA.model.Habitacion;
import com.example.WorkshopJPA.model.Reserva;
import com.example.WorkshopJPA.repository.ClienteRepository;
import com.example.WorkshopJPA.repository.HabitacionRepository;
import com.example.WorkshopJPA.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final HabitacionRepository habitacionRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository, HabitacionRepository habitacionRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public ResponseEntity<?> registrarReserva(Reserva reserva) {
        try {
            Cliente cliente = clienteRepository.findById(reserva.getCliente().getIdCliente()).orElse(null);
            ResponseEntity<?> validationResult = validarReserva(reserva, cliente);

            if (validationResult != null) {
                return validationResult;
            }

            // Generar un número de reserva único (GUID)
            UUID numeroReserva = UUID.randomUUID();
            reserva.setIdReserva(numeroReserva);

            // Continuar con el proceso de reserva si la validación es exitosa
            long precioTotal = calcularPrecioTotal(reserva);
            reserva.setTotalPagar(precioTotal);

            Reserva reservaRegistrada = reservaRepository.save(reserva);

            // Mapear la entidad Reserva a ReservaDTO
            ReservaDTO reservaDTO = new ReservaDTO();
            reservaDTO.setIdReserva(reservaRegistrada.getIdReserva());
            reservaDTO.setFechaReserva(reservaRegistrada.getFechaReserva());
            reservaDTO.setTotalPagar(reservaRegistrada.getTotalPagar());

            // Obtener la lista de números de habitaciones
            List<Integer> numerosHabitaciones = reservaRegistrada.getHabitaciones()
                    .stream()
                    .map(Habitacion::getNumero)
                    .collect(Collectors.toList());
            reservaDTO.setNumerosHabitaciones(numerosHabitaciones);

            return ResponseEntity.status(HttpStatus.CREATED).body(reservaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    private ResponseEntity<?> validarReserva(Reserva reserva, Cliente cliente) {
        // Validar la fecha (no nula y posterior a la fecha actual)
        LocalDate fechaActual = LocalDate.now();
        if (reserva.getFechaReserva() == null || reserva.getFechaReserva().isBefore(fechaActual)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La fecha de reserva no es válida.");
        }
        // Validar existencia de cliente
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no está registrado.");
        }
        // Validar existencia de habitaciones
        List<Long> habitacionesNoEncontradas = new ArrayList<>();

        for (Habitacion habitacion : reserva.getHabitaciones()) {
            Habitacion habitacionExistente = habitacionRepository.findById(habitacion.getIdHabitacion()).orElse(null);

            if (habitacionExistente == null) {
                habitacionesNoEncontradas.add(habitacion.getIdHabitacion());
            }
        }

        if (!habitacionesNoEncontradas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Las siguientes habitaciones no existen: " + habitacionesNoEncontradas);
        }

        // Verificar disponibilidad de las habitaciones
        List<Habitacion> habitacionesDisponibles = habitacionRepository.findHabitacionesDisponiblesPorFecha(reserva.getFechaReserva());
        List<Long> habitacionesNoDisponibles = new ArrayList<>();

        for (Habitacion habitacion : reserva.getHabitaciones()) {
            if (habitacionesDisponibles.stream().noneMatch(h -> h.getIdHabitacion() == habitacion.getIdHabitacion())) {
                habitacionesNoDisponibles.add(habitacion.getIdHabitacion());
            }
        }

        if (!habitacionesNoDisponibles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Las siguientes habitaciones no están disponibles en la fecha seleccionada: " + habitacionesNoDisponibles);
        }

        return null; // La reserva es válida
    }

    private long calcularPrecioTotal(Reserva reserva) {
        long precioBaseTotal = 0;

        for (Habitacion habitacion : reserva.getHabitaciones()) {
            long idHabitacion = habitacion.getIdHabitacion();

            // Buscar el precioBase en la base de datos
            Habitacion habitacionConPrecio = habitacionRepository.findById(idHabitacion).orElse(null);

            if (habitacionConPrecio != null) {
                long precioBaseHabitacion = habitacionConPrecio.getPrecioBase();

                // Aplicar descuento según la fecha de reserva
                LocalDate fechaReserva = reserva.getFechaReserva();
                LocalDate fechaActual = LocalDate.now();
                long diasDeAnticipacion = ChronoUnit.DAYS.between(fechaActual, fechaReserva);

                if (diasDeAnticipacion > 15) {
                    // Aplicar un descuento del 20%
                    precioBaseHabitacion -= (long) (precioBaseHabitacion * 0.2);

                    // Si la habitación es premium, aplicar un descuento adicional del 5%
                    if ("p".equalsIgnoreCase(habitacionConPrecio.getTipo())) {
                        precioBaseHabitacion -= (long) (precioBaseHabitacion * 0.05);
                    }
                }

                precioBaseTotal += precioBaseHabitacion;
            }
        }

        return precioBaseTotal;
    }





    public ResponseEntity<?> obtenerReservaPorId(UUID id) {
        try {
            Reserva reserva = reservaRepository.findByIdReserva(id).orElse(null);

            if (reserva != null) {
                return ResponseEntity.status(HttpStatus.OK).body(reserva);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodasLasReservas() {
        try {
            List<Reserva> reservas = reservaRepository.findAll();

            if (!reservas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(reservas);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay reservas registradas.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> actualizarReserva(UUID id, Reserva reservaActualizada) {
        try {
            Reserva reservaExistente = reservaRepository.findByIdReserva(id).orElse(null);

            if (reservaExistente != null) {
                reservaActualizada.setIdReserva(id);
                Reserva reservaActualizadaResult = reservaRepository.save(reservaActualizada);
                return ResponseEntity.status(HttpStatus.OK).body(reservaActualizadaResult);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada para actualizar.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminarReserva(Long id) {
        try {
            Reserva reservaExistente = reservaRepository.findById(id).orElse(null);

            if (reservaExistente != null) {
                reservaRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada para eliminar.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
