/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aplicacion.personas.controller;

import com.example.aplicacion.personas.model.PersonaModel;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.aplicacion.personas.repository.PersonaRepository;
import com.example.aplicacion.personas.service.PersonaService;
import com.example.aplicacion.personas.utils.CustomResponse;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/")
public class PersonaControlador {

    @Autowired 
    private PersonaService personaService;

    
    @PostMapping("/personas")
    public CustomResponse registrarPersona(@RequestBody PersonaModel persona) {
        CustomResponse customeResponse = new CustomResponse();
        personaService.createPersona(persona);        
        return customeResponse;
    }
    
    @GetMapping("/personas")
    public CustomResponse getPersonas(){
        CustomResponse customeResponse = new CustomResponse();
        customeResponse.setData(personaService.getPersonas());
        return customeResponse;
    }
    
   @GetMapping("/personas/{id}")
    public CustomResponse getPersonaById(@PathVariable Long id) {
        CustomResponse customeResponse = new CustomResponse();
        customeResponse.setData(personaService.getPersonaById(id));
        return customeResponse;
    }
    
    @PutMapping("/personas/{id}")
    public CustomResponse updatePersona(@RequestBody PersonaModel persona, @PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        personaService.updatePersona(persona, id);        
        return customResponse;       
    }
    
    @DeleteMapping("/personas/{id}")
    public CustomResponse deletePersona(@PathVariable Long id){
        CustomResponse customeResponse = new CustomResponse();
        personaService.deletePersona(id);        
        return customeResponse;       
    }
}
