/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aplicacion.personas.implement.service;

import com.example.aplicacion.personas.exception.PersonaNotFoundException;
import com.example.aplicacion.personas.model.PersonaModel;
import com.example.aplicacion.personas.repository.PersonaRepository;
import com.example.aplicacion.personas.service.PersonaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PersonaServiceImplement implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public void createPersona(PersonaModel persona) {
        Optional<PersonaModel> existingPersona = personaRepository.findByNombre(persona.getNombre());
        if (existingPersona.isPresent()) {
            // Si el nombre ya está en uso, lanzar una excepción
            throw new IllegalArgumentException("El nombre '" + persona.getNombre() + "' ya está en uso");
        } else {
            // Si el nombre no está duplicado, guardar la persona
            personaRepository.save(persona);
        }
    }

    @Override
    public List<PersonaModel> getPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public PersonaModel getPersonaById(Long id) {
        Optional<PersonaModel> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            return personaOptional.get();
        } else {
            // Si no se encuentra la persona con el ID proporcionado, lanzar una excepción
            throw new PersonaNotFoundException("No se encontró la persona con el ID proporcionado: " + id);
        }
    }

    @Override
    public void updatePersona(PersonaModel personaModel, Long id) {
        Optional<PersonaModel> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) { // Verifica si la persona existe
            PersonaModel existingPersona = personaOptional.get();
            existingPersona.setNombre(personaModel.getNombre());
            existingPersona.setApellidos(personaModel.getApellidos());
            existingPersona.setAñoNacimiento(personaModel.getAñoNacimiento());
            existingPersona.setDireccion(personaModel.getDireccion());
            existingPersona.setTelefono(personaModel.getTelefono());
            existingPersona.setCorreo(personaModel.getCorreo());

            try {
                personaRepository.save(existingPersona); // Guarda la persona actualizada
            } catch (Exception e) {
                // Maneja la excepción
                throw new RuntimeException("Error al actualizar la persona: " + e.getMessage());
            }
        } else {
            // Si no se encuentra la persona con el ID proporcionado, lanzar una excepción
            throw new PersonaNotFoundException("No se encontró la persona con el ID proporcionado: " + id);
        }
    }

    @Override
    public void deletePersona(Long id) {
        try {
            personaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Si no se encuentra la persona con el ID proporcionado, lanzar una excepción
            throw new PersonaNotFoundException("No se encontró la persona con el ID proporcionado: " + id);
        }
    }

}
