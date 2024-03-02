/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.aplicacion.personas.repository;

import com.example.aplicacion.personas.model.HijoModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Admin
 */
public interface HijoRepository extends JpaRepository<HijoModel, Long>{

    Optional<HijoModel> findById(Long id);
}
