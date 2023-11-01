package com.example.WorkshopJPA.controller;

import com.example.WorkshopJPA.model.Habitacion;
import com.example.WorkshopJPA.service.HabitacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping
    public ResponseEntity<?> registrarHabitacion(@RequestBody @Valid Habitacion habitacion) {
        return habitacionService.registrarHabitacion(habitacion);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodasLasHabitaciones() {
        return habitacionService.obtenerTodasLasHabitaciones();
    }

    @GetMapping("/{idHabitacion}")
    public ResponseEntity<?> obtenerHabitacionPorId(@PathVariable Long idHabitacion) {
        return habitacionService.obtenerHabitacionPorId(idHabitacion);
    }

    @PutMapping("/{idHabitacion}")
    public ResponseEntity<?> actualizarHabitacion(@PathVariable Long idHabitacion, @RequestBody @Valid Habitacion habitacion) {
        return habitacionService.actualizarHabitacion(idHabitacion, habitacion);
    }

    @DeleteMapping("/{idHabitacion}")
    public ResponseEntity<?> eliminarHabitacion(@PathVariable Long idHabitacion) {
        return habitacionService.eliminarHabitacion(idHabitacion);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<?> obtenerHabitacionesDisponiblesPorFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return habitacionService.obtenerHabitacionesDisponiblesPorFecha(fecha);
    }

    @GetMapping("/disponibles/filtradas")
    public ResponseEntity<?> obtenerHabitacionesDisponiblesPorFechaYTipo(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(value = "tipo", required = false) String tipo) {
        return habitacionService.obtenerHabitacionesDisponiblesPorFechaYTipo(fecha, tipo);
    }
}
