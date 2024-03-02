/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aplicacion.personas.controller;

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
import com.example.aplicacion.personas.utils.CustomResponse;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/")
public class HijoControlador {

    @Autowired 
    private HijoService hijoService;

    
    @PostMapping("/hijos")
    public CustomResponse registrarHijo(@RequestBody HijoModel hijo) {
        CustomResponse customeResponse = new CustomResponse();
        hijoService.createHijo(hijo);        
        return customeResponse;
    }
    
    @GetMapping("/hijos")
    public CustomResponse getHijos(){
        CustomResponse customeResponse = new CustomResponse();
        customeResponse.setData(hijoService.getHijos());
        return customeResponse;
    }
    
   @GetMapping("/hijos/{id}")
    public CustomResponse getHijoById(@PathVariable Long id) {
        CustomResponse customeResponse = new CustomResponse();
        customeResponse.setData(hijoService.getHijoById(id));
        return customeResponse;
    }
    
    @PutMapping("/hijos/{id}")
    public CustomResponse updateHijo(@RequestBody HijoModel hijo, @PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        hijoService.updateHijo(hijo, id);        
        return customResponse;       
    }
    
    @DeleteMapping("/hijos/{id}")
    public CustomResponse deleteHijo(@PathVariable Long id){
        CustomResponse customeResponse = new CustomResponse();
        hijoService.deleteHijo(id);        
        return customeResponse;       
    }

    
}
