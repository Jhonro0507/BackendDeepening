package com.ApiRestBasica.APIRestEjemplo.modelo;

public class Persona {
    private String nombre;
    private String documento;
    private String apellido;
    private String ciudad;

    public Persona(String nombre, String documento, String apellido, String ciudad) {
        this.nombre = nombre;
        this.documento = documento;
        this.apellido = apellido;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
