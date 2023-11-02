package com.example.WorkshopJPA;

import com.example.WorkshopJPA.model.Cliente;
import com.example.WorkshopJPA.model.Habitacion;
import com.example.WorkshopJPA.model.Reserva;
import com.example.WorkshopJPA.repository.ClienteRepository;
import com.example.WorkshopJPA.repository.HabitacionRepository;
import com.example.WorkshopJPA.repository.ReservaRepository;
import com.example.WorkshopJPA.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class ReservaServiceTest {

    private ReservaService reservaService;
    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;
    private HabitacionRepository habitacionRepository;

    @BeforeEach
    public void setUp() {
        reservaRepository = Mockito.mock(ReservaRepository.class);
        clienteRepository = Mockito.mock(ClienteRepository.class);
        habitacionRepository = Mockito.mock(HabitacionRepository.class);
        reservaService = new ReservaService(reservaRepository, clienteRepository, habitacionRepository);
    }

    @Test
    public void testRegistrarReserva_Valid() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFechaReserva(LocalDate.now().plusDays(1));
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(1L);
        reserva.setHabitaciones(List.of(habitacion));
        reserva.setTotalPagar(100);

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));
        when(habitacionRepository.findById(1L)).thenReturn(java.util.Optional.of(habitacion));
        when(habitacionRepository.findHabitacionesDisponiblesPorFecha(any())).thenReturn(List.of(habitacion));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        // Act
        ResponseEntity<?> responseEntity = reservaService.registrarReserva(reserva);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }


    @Test
    public void testRegistrarReserva_InvalidFecha() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFechaReserva(LocalDate.now().minusDays(1)); // Fecha pasada
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(1L);
        reserva.setHabitaciones(List.of(habitacion));
        reserva.setTotalPagar(100);

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));

        // Act
        ResponseEntity<?> responseEntity = reservaService.registrarReserva(reserva);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testRegistrarReserva_InvalidCliente() {
        // Arrange
        Reserva reserva = new Reserva();
        reserva.setCliente(new Cliente());
        reserva.getCliente().setIdCliente(1L);
        reserva.setFechaReserva(LocalDate.now().plusDays(1));
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(1L);
        reserva.setHabitaciones(List.of(habitacion));
        reserva.setTotalPagar(100);

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<?> responseEntity = reservaService.registrarReserva(reserva);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testRegistrarReserva_InvalidHabitacionesNoExistentes() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFechaReserva(LocalDate.now().plusDays(1));
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(1L);
        reserva.setHabitaciones(List.of(habitacion));
        reserva.setTotalPagar(100);

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));
        when(habitacionRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<?> responseEntity = reservaService.registrarReserva(reserva);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testRegistrarReserva_InvalidHabitacionesNoDisponibles() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFechaReserva(LocalDate.now().plusDays(1));
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(1L);
        reserva.setHabitaciones(List.of(habitacion));
        reserva.setTotalPagar(100);

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));
        when(habitacionRepository.findById(1L)).thenReturn(java.util.Optional.of(habitacion));

        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        when(habitacionRepository.findHabitacionesDisponiblesPorFecha(any())).thenReturn(habitacionesDisponibles);

        // Act
        ResponseEntity<?> responseEntity = reservaService.registrarReserva(reserva);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testObtenerReservaPorId_Existente() {
        // Arrange
        Reserva reserva = new Reserva();
        UUID idReserva = UUID.randomUUID();
        reserva.setIdReserva(idReserva);

        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(java.util.Optional.of(reserva));

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerReservaPorId(idReserva);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reserva, responseEntity.getBody());
    }

    @Test
    public void testObtenerReservaPorId_NoExistente() {
        // Arrange
        UUID idReservaNoExistente = UUID.randomUUID();

        when(reservaRepository.findByIdReserva(idReservaNoExistente)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerReservaPorId(idReservaNoExistente);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testObtenerReservaPorId_InternalServerError() {
        // Arrange
        UUID idReserva = UUID.randomUUID();

        when(reservaRepository.findByIdReserva(idReserva)).thenThrow(new RuntimeException("Error interno"));

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerReservaPorId(idReserva);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error interno", responseEntity.getBody());
    }

    @Test
    public void testObtenerReservaPorId_OtraExcepcion() {
        // Arrange
        UUID idReserva = UUID.randomUUID();

        when(reservaRepository.findByIdReserva(idReserva)).thenThrow(new NullPointerException("Otra excepción"));

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerReservaPorId(idReserva);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Otra excepción", responseEntity.getBody());
    }

    @Test
    public void testObtenerTodasLasReservas_NoVacias() {
        // Arrange
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(new Reserva());
        reservas.add(new Reserva());

        when(reservaRepository.findAll()).thenReturn(reservas);

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerTodasLasReservas();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reservas, responseEntity.getBody());
    }

    @Test
    public void testObtenerTodasLasReservas_Vacias() {
        // Arrange
        List<Reserva> reservas = new ArrayList<>();

        when(reservaRepository.findAll()).thenReturn(reservas);

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerTodasLasReservas();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    @Test
    public void testObtenerTodasLasReservas_InternalServerError() {
        // Arrange
        when(reservaRepository.findAll()).thenThrow(new RuntimeException("Error interno"));

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerTodasLasReservas();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error interno", responseEntity.getBody());
    }

    @Test
    public void testObtenerTodasLasReservas_OtraExcepcion() {
        // Arrange
        when(reservaRepository.findAll()).thenThrow(new NullPointerException("Otra excepción"));

        // Act
        ResponseEntity<?> responseEntity = reservaService.obtenerTodasLasReservas();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Otra excepción", responseEntity.getBody());
    }

    @Test
    public void testActualizarReserva_Existente() {
        // Arrange
        Reserva reservaExistente = new Reserva();
        UUID idReserva = UUID.randomUUID();
        reservaExistente.setIdReserva(idReserva);

        Reserva reservaActualizada = new Reserva();
        reservaActualizada.setIdReserva(idReserva);

        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(java.util.Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaActualizada);

        // Act
        ResponseEntity<?> responseEntity = reservaService.actualizarReserva(idReserva, reservaActualizada);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reservaActualizada, responseEntity.getBody());
    }

    @Test
    public void testActualizarReserva_NoExistente() {
        // Arrange
        UUID idReservaNoExistente = UUID.randomUUID();
        Reserva reservaActualizada = new Reserva();

        when(reservaRepository.findByIdReserva(idReservaNoExistente)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<?> responseEntity = reservaService.actualizarReserva(idReservaNoExistente, reservaActualizada);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testActualizarReserva_InternalServerError() {
        // Arrange
        UUID idReserva = UUID.randomUUID();
        Reserva reservaActualizada = new Reserva();

        when(reservaRepository.findByIdReserva(idReserva)).thenThrow(new RuntimeException("Error interno"));

        // Act
        ResponseEntity<?> responseEntity = reservaService.actualizarReserva(idReserva, reservaActualizada);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error interno", responseEntity.getBody());
    }

    @Test
    public void testActualizarReserva_OtraExcepcion() {
        // Arrange
        UUID idReserva = UUID.randomUUID();
        Reserva reservaActualizada = new Reserva();

        when(reservaRepository.findByIdReserva(idReserva)).thenThrow(new NullPointerException("Otra excepción"));

        // Act
        ResponseEntity<?> responseEntity = reservaService.actualizarReserva(idReserva, reservaActualizada);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Otra excepción", responseEntity.getBody());
    }

    @Test
    public void testEliminarReserva_Existente() {
        // Arrange
        Reserva reservaExistente = new Reserva();
        Long idReserva = 1L;

        when(reservaRepository.findById(idReserva)).thenReturn(java.util.Optional.of(reservaExistente));

        // Act
        ResponseEntity<?> responseEntity = reservaService.eliminarReserva(idReserva);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }


    @Test
    public void testEliminarReserva_NoExistente() {
        // Arrange
        Long idReservaNoExistente = 1L;

        when(reservaRepository.findById(idReservaNoExistente)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<?> responseEntity = reservaService.eliminarReserva(idReservaNoExistente);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testEliminarReserva_InternalServerError() {
        // Arrange
        Long idReserva = 1L;

        when(reservaRepository.findById(idReserva)).thenReturn(java.util.Optional.of(new Reserva()));
        doThrow(new RuntimeException("Error interno")).when(reservaRepository).deleteById(idReserva);

        // Act
        ResponseEntity<?> responseEntity = reservaService.eliminarReserva(idReserva);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error interno", responseEntity.getBody());
    }

    @Test
    public void testEliminarReserva_OtraExcepcion() {
        // Arrange
        Long idReserva = 1L;

        when(reservaRepository.findById(idReserva)).thenReturn(java.util.Optional.of(new Reserva()));
        doThrow(new NullPointerException("Otra excepción")).when(reservaRepository).deleteById(idReserva);

        // Act
        ResponseEntity<?> responseEntity = reservaService.eliminarReserva(idReserva);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Otra excepción", responseEntity.getBody());
    }

}

