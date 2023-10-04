package com.ApiRestBasica.APIRestEjemplo.controlador;

import com.ApiRestBasica.APIRestEjemplo.modelo.Persona;
import com.ApiRestBasica.APIRestEjemplo.servicio.PersonaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/personas")
public class PersonaController {
    ArrayList<Persona> personas = new ArrayList<>(); // Se debe crear la lista que va
    // a contener las personas. Aunque ya "exista" un arraylist llamado personas en
    // PersonaServicio esta solo sirve para definir los métodos en PersonaServicio,
    // aquí toca crearla para poder usarla como base de datos

    PersonaServicio service = new PersonaServicio(); // Se debe instanciar la clase
    // PersonaServicio para poder usar los métodos creados en dicha clase, los cuales
    // serán aplicados sobre el ArrayList personas instanciado aquí mismo

    //GetMapping: obtener información
    //PostMapping: creación de objetos
    //DeleteMapping: borrar información
    //PutMapping: actualizar información

    @GetMapping()
    public ArrayList<Persona> listarPersonas(){
        return this.service.listarPersonas();
    }

    @GetMapping("{cedula}") // Esta cédula es que la que se pide en @PathVariable
    public Optional<Persona> obtenerPersonaPorCedula (@PathVariable("cedula") String cedula) {
        return service.obtenerPersonaPorCedula(cedula);
    }

    @PostMapping() // Con @RequestBody se pasa un objeto completo
    public Persona crearPersona(@RequestBody Persona persona){
        return this.service.crearPersona(persona);
    }

    @PutMapping()
    public Optional<Persona> actualizarPersona (@RequestBody Persona persona){
        return this.service.modificarPersona(persona);
    }

    @DeleteMapping("{cedula}")
    public boolean eliminarPorCedula (@PathVariable("cedula") String cedula){
        return this.service.eliminarPersonaPorCedula(cedula);
    }

}
