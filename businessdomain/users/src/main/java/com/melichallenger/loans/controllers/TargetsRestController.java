/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.melichallenger.loans.controllers;

import com.melichallenger.loans.entities.Targets;
import com.melichallenger.loans.repository.TargetsRepository;
import com.melichallenger.resources.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author biosx1706
 */
@RestController
@CrossOrigin
@RequestMapping("/target")
public class TargetsRestController {
    @Autowired
    TargetsRepository targetsRepository;
    
    @GetMapping
    @Operation(summary = "Obtener Targets", description = "Obtiene la lista de todos los targets registrados")    
    public List<Targets> list() {
        List<Targets> targets = targetsRepository.findAll();
        return targets;
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener target", description = "Obtiene un target por id")
    public Targets  get(@Parameter(description="ID del target a buscar") @PathVariable long id) throws ResourceNotFoundException {
        Targets target = targetsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Target no encontrado, con id :: " + id));
        return target;
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar target", description = "Actualizar datos de un target por id")
    public ResponseEntity<Targets> put(@PathVariable long id, @RequestBody Targets input) throws ResourceNotFoundException {
        Targets target = targetsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Target no encontrado, con id :: " + id));
        target.setAmount_max(input.getAmount_max());
        target.setCant_max(input.getCant_max());
        target.setCant_min(input.getCant_min());
        target.setAmount_min(input.getAmount_min());
        target.setRate(input.getRate());
        target.setDescription(((input.getDescription() == null || input.getDescription().length()==0 || input.getDescription().isEmpty()) ? target.getDescription() : input.getDescription()));
        target.setStatus(((target.getStatus() == null || target.getStatus().length()==0 || target.getStatus().isEmpty()) ? target.getStatus(): input.getStatus()));
        
        final Targets updatedTarget = targetsRepository.save(target);
        return ResponseEntity.ok(updatedTarget);
    }
    
    @PostMapping
    @Operation(summary = "Agregar un target", description = "Crear un target nuevo")
    public ResponseEntity<Targets> post(@RequestBody Targets input) {
        Targets save = targetsRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar target", description = "Actualizar datos de un target por id marcandolo para eliminar ")
    public ResponseEntity<Targets> delete(@PathVariable long id) throws ResourceNotFoundException, Exception {
        Targets target = targetsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        if("deleted".equals(target.getStatus())) {
            throw new Exception("El usuario ya ha sido marcado para eliminar, con id :: " + id);
        }
        target.setStatus("deleted");
        
        final Targets updatedTarget = targetsRepository.save(target);
        return ResponseEntity.ok(updatedTarget);
    }
    
}
