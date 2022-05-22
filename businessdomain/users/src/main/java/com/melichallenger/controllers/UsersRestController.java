/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.melichallenger.controllers;

import com.melichallenger.entities.Users;
import com.melichallenger.resources.ResourceNotFoundException;
import com.melichallenger.respository.UsersRepository;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/users")
public class UsersRestController {
    
    @Autowired
    UsersRepository usersRepository;
            
    @GetMapping()
    public List<Users> list() {
        return usersRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Users  get(@PathVariable long id) throws ResourceNotFoundException {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        return user;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Users> put(@PathVariable long id, @RequestBody Users input) throws ResourceNotFoundException {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        user.setLastname(input.getLastname());
        user.setAddress(input.getAddress());
        user.setName(input.getName());
        user.setPhone(input.getPhone());
        
        final Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Users input) {
        Users save = usersRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @DeleteMapping("/{id}")
    public Map < String, Boolean > delete(@PathVariable String id) {
        Map < String, Boolean > response = new HashMap < > ();
        response.put("not-permitido", Boolean.FALSE);
        return response;
    }
        
}
