/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.aplicacion.personas.service;

import com.example.aplicacion.personas.model.HijoModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface HijoService {
    public void createHijo(HijoModel hijo);
    
    public List getHijos();
    public HijoModel getHijoById(Long id);
    public void updateHijo(HijoModel hijoModel, Long id);
    public void deleteHijo(Long id);
}
