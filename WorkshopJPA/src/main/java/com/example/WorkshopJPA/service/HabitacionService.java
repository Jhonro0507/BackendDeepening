package com.example.WorkshopJPA.service;

import com.example.WorkshopJPA.model.Habitacion;
import com.example.WorkshopJPA.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public ResponseEntity<?> registrarHabitacion(Habitacion habitacion) {
        try {
            Habitacion habitacionRegistrada = habitacionRepository.save(habitacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(habitacionRegistrada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodasLasHabitaciones() {
        try {
            List<Habitacion> habitaciones = habitacionRepository.findAll();

            if (!habitaciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(habitaciones);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se han agregado habitaciones");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerHabitacionPorId(Long idHabitacion) {
        try {
            Habitacion habitacion = habitacionRepository.findById(idHabitacion).orElse(null);

            if (habitacion != null) {
                return ResponseEntity.status(HttpStatus.OK).body(habitacion);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habitación no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> actualizarHabitacion(Long idHabitacion, Habitacion habitacion) {
        try {
            Habitacion habitacionExistente = habitacionRepository.findById(idHabitacion).orElse(null);

            if (habitacionExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habitación no encontrada");
            }

            habitacionExistente.setNumero(habitacion.getNumero());
            habitacionExistente.setTipo(habitacion.getTipo());
            habitacionExistente.setPrecioBase(habitacion.getPrecioBase());

            Habitacion habitacionActualizada = habitacionRepository.save(habitacionExistente);
            return ResponseEntity.status(HttpStatus.OK).body(habitacionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminarHabitacion(Long idHabitacion) {
        try {
            Habitacion habitacionExistente = habitacionRepository.findById(idHabitacion).orElse(null);

            if (habitacionExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habitación no encontrada");
            }

            habitacionRepository.delete(habitacionExistente);
            return ResponseEntity.status(HttpStatus.OK).body("Habitación eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerHabitacionesDisponiblesPorFecha(LocalDate fecha) {
        try {
            List<Habitacion> habitacionesDisponibles = habitacionRepository.findHabitacionesDisponiblesPorFecha(fecha);

            if (habitacionesDisponibles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay habitaciones disponibles para la fecha especificada.");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(habitacionesDisponibles);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerHabitacionesDisponiblesPorFechaYTipo(LocalDate fecha, String tipo) {
        try {
            List<Habitacion> habitacionesDisponibles = habitacionRepository.findHabitacionesDisponiblesPorFechaYTipo(fecha, tipo);

            if (habitacionesDisponibles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay habitaciones disponibles para la fecha y tipo especificados.");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(habitacionesDisponibles);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

