/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aplicacion.personas.implement.service;

import com.example.aplicacion.personas.model.HijoModel;
import com.example.aplicacion.personas.repository.HijoRepository;
import com.example.aplicacion.personas.service.HijoService;
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
public class HijoServiceImplement implements HijoService {

    @Autowired
    private HijoRepository hijoRepository;

    @Override
    public void createHijo(HijoModel hijo) {
        hijoRepository.save(hijo);
    }

    @Override
    public List getHijos() {
        return hijoRepository.findAll();
    }

    @Override
    public HijoModel getHijoById(Long id) {
        Optional<HijoModel> hijoOptional = hijoRepository.findById(id);
        return hijoOptional.orElse(null);
    }

    @Override
    public void updateHijo(HijoModel hijoModel, Long id) {
        Optional<HijoModel> hijoOptional = hijoRepository.findById(id);
        if (hijoOptional.isPresent()) { // Verifica si la persona existe
            HijoModel existingHijo = hijoOptional.get();
            existingHijo.setNombre(hijoModel.getNombre());
            existingHijo.setEdad(hijoModel.getEdad());
            try {
                hijoRepository.save(existingHijo); // Guarda la persona actualizada
            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar al hijo: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("No se encontró al hijo con el ID proporcionado: " + id);
        }
    }

    @Override
    public void deleteHijo(Long id) {
        try {
            hijoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No se encontró el hijo con el ID proporcionado: " + id);
        }
    }

}
