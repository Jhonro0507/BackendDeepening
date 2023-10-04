package com.ApiRestBasica.APIRestEjemplo.servicio;

import com.ApiRestBasica.APIRestEjemplo.modelo.Persona;

import java.util.ArrayList;
import java.util.Optional;

public class PersonaServicio {
    // Creación de lista simulando la base de datos
    ArrayList<Persona> personas = new ArrayList<>();
    // Crear método para listar todas las personas
    public ArrayList<Persona> listarPersonas (){
        return this.personas;
    }
    // Crear método para obtener una persona por su cédula
    public Optional<Persona> obtenerPersonaPorCedula(String cedula){
        return this.personas
                .stream()
                .filter(p -> p.getDocumento().equals(cedula))
                .findFirst();
    }
    // Creo un método para la creación de personas
    public Persona crearPersona(Persona persona){
        this.personas.add(persona);
        return persona;
    }

    // Método para actualizar una persona
    public Optional<Persona> modificarPersona (Persona persona){
        return this.personas.stream()
                .filter(p -> p.getDocumento().equals(persona.getDocumento()))
                .findFirst().map(p -> {
                    p.setNombre(persona.getNombre());
                    p.setApellido(persona.getApellido());
                    p.setCiudad(persona.getCiudad());
                    return p;
                });
    }

    // Método para eliminar persona
    public boolean eliminarPersonaPorCedula (String cedula){
        return this.personas.removeIf(p -> p.getDocumento().equals(cedula));
    }
}
