package com.example.clinica.odontologica.service;

import com.example.clinica.odontologica.repository.OdontologoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdontologoService {
    //[Servicio] -> [Repositorio]
    private OdontologoRepositorio odontologoRepositorio;
    @Autowired
    public OdontologoService(OdontologoRepositorio odontologoRepositorio) {
        this.odontologoRepositorio = odontologoRepositorio;
    }
}
