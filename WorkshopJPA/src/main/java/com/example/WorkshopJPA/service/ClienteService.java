package com.example.WorkshopJPA.service;

import com.example.WorkshopJPA.model.Cliente;
import com.example.WorkshopJPA.model.Reserva;
import com.example.WorkshopJPA.repository.ClienteRepository;
import com.example.WorkshopJPA.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ReservaRepository reservaRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ReservaRepository reservaRepository) {
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }

    public ResponseEntity<?> registrarCliente(Cliente cliente) {
        try {
            Cliente clienteRegistrado = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteRegistrado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerClientePorId(Long idCliente) {
        try {
            Cliente cliente = clienteRepository.findById(idCliente).orElse(null);

            if (cliente != null) {
                return ResponseEntity.status(HttpStatus.OK).body(cliente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente con id " + idCliente + " no se encontró");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodosLosClientes() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();

            if (!clientes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(clientes);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron clientes");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> actualizarCliente(Long id, Cliente clienteActualizado) {
        try {
            Cliente clienteExistente = clienteRepository.findById(id).orElse(null);

            if (clienteExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente con id " + id + " no se encontró");
            }

            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setApellido(clienteActualizado.getApellido());
            clienteExistente.setCedula(clienteActualizado.getCedula());
            clienteExistente.setDireccion(clienteActualizado.getDireccion());
            clienteExistente.setEdad(clienteActualizado.getEdad());
            clienteExistente.setCorreoElectronico(clienteActualizado.getCorreoElectronico());

            clienteRepository.save(clienteExistente);

            return ResponseEntity.status(HttpStatus.OK).body(clienteExistente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminarCliente(Long id) {
        try {
            Cliente clienteExistente = clienteRepository.findById(id).orElse(null);

            if (clienteExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente con id " + id + " no se encontró");
            }

            clienteRepository.delete(clienteExistente);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerReservasPorCedula(String cedula) {
        try {
            List<Reserva> reservas = reservaRepository.obtenerReservasPorCedula(cedula);// Implementa este método en tu repositorio ReservaRepository
            return ResponseEntity.status(HttpStatus.OK).body(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

