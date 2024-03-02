/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aplicacion.personas.controller;

import com.example.aplicacion.personas.exception.PersonaNotFoundException;
import com.example.aplicacion.personas.model.HijoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.aplicacion.personas.service.HijoService;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/hijos")
public class HijoControlador {

    @Autowired
    private HijoService hijoService;

    @PostMapping
    public ResponseEntity<?> crearHijo(@RequestBody HijoModel hijo) {
        hijoService.createHijo(hijo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<HijoModel>> obtenerHijos(){
        List<HijoModel> hijos = hijoService.getHijos();
        return ResponseEntity.ok(hijos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HijoModel> obtenerHijoPorId(@PathVariable Long id) {
        HijoModel hijo = hijoService.getHijoById(id);
        return ResponseEntity.ok(hijo);
    }
    
    @GetMapping("/per/{id}")
    public ResponseEntity<List<HijoModel>>  obtenerHijoPorPersona(@PathVariable Long id) {
        List<HijoModel> hijos = hijoService.getHijosByPersonaId(id);
        return ResponseEntity.ok(hijos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHijo(@RequestBody HijoModel hijo, @PathVariable Long id){
        hijoService.updateHijo(hijo, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHijo(@PathVariable Long id){
        hijoService.deleteHijo(id);
        return ResponseEntity.noContent().build();
    }

    // Manejo de excepciones
    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<String> manejarExcepcionPersonaNotFound(PersonaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    
}
