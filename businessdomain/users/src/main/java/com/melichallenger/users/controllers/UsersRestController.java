/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.melichallenger.users.controllers;

import com.github.javafaker.Faker;
import com.melichallenger.users.entities.Users;
import com.melichallenger.resources.ResourceNotFoundException;
import com.melichallenger.users.respository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador para el manejos de usuarios
 * @author biosx1706
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersRestController {
    
    @Autowired
    UsersRepository usersRepository;
            
    /**
     * Obtener todos los usuarios
     * @return Lista de usuarios
     */
    @GetMapping()
    @Operation(summary = "Obtener usuarios", description = "Obtiene la lista de todos los usuarios registrados")    
    public List<Users> list() {
        List<Users> users = usersRepository.findAll();
        return users;
    }
    
    /**
     * Obtener un usuario
     * @param id ID del usuario a buscar
     * @return Objeto con los datos del usuario
     * @throws ResourceNotFoundException Excepcion cuando un objeto no existe
     */
    @GetMapping("/user/{id}")
    @Operation(summary = "Obtener usuario", description = "Obtiene un usuario por id")
    public Users  get(@Parameter(description="ID del usuario a buscar") @PathVariable long id) throws ResourceNotFoundException {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        return user;
    }
    
    /**
     * Actualizar informacion de un usuario
     * @param id ID del usuario a actualizar
     * @param input Datos del usuario
     * @return Objeto usuraio con los datos actualizados
     * @throws ResourceNotFoundException Excepcion cuando un objeto no existe
     */
    @PutMapping("/user/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualizar datos de un usuario por id")
    public ResponseEntity<Users> put(@PathVariable long id, @RequestBody Users input) throws ResourceNotFoundException {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        user.setLastname(((input.getLastname() == null || input.getLastname().length()==0 || input.getLastname().isEmpty()) ? user.getLastname(): input.getLastname()));
        user.setAddress(((input.getAddress() == null || input.getAddress().length()==0 || input.getAddress().isEmpty()) ? user.getAddress() : input.getAddress()));
        user.setName(((input.getName()== null || input.getName().length()==0 || input.getName().isEmpty()) ? user.getName() : input.getName()));
        user.setPhone(((input.getPhone() == null || input.getPhone().length()==0 || input.getPhone().isEmpty()) ? user.getPhone(): input.getPhone()));
        user.setStatus(((input.getStatus() == null || input.getStatus().length()==0 || input.getStatus().isEmpty()) ? user.getStatus(): input.getStatus()));
        
        final Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    /**
     * Activar un usuario inactivo
     * @param id ID del usuario a activar
     * @return Objeto User con los datos
     * @throws ResourceNotFoundException Excepcion cuando un objeto no existe
     * @throws Exception Manejo de excepciones
     */
    @PutMapping("/user/activate/{id}")
    @Operation(summary = "Activar usuario", description = "Actualizar un usuario inactivo por id")
    public ResponseEntity<Users> activate(@PathVariable long id) throws ResourceNotFoundException, Exception {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        
        if(!"inactive".equals(user.getStatus())) {
            throw new Exception("El usuario no esta inactivo, con id :: " + id);
        }
        user.setStatus("active");
        
        final Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    /**
     *  Desctivar un usuario activo
     * @param id ID del usuario a activar
     * @return Datos del usuario
     * @throws ResourceNotFoundException Excepcion cuando un objeto no existe
     * @throws Exception  Manejo de excepciones
     */
    @PutMapping("/user/deactivate/{id}")
    @Operation(summary = "Desactivar usuario", description = "Actualizar un usuario activo por id")
    public ResponseEntity<Users> deactivate(@PathVariable long id) throws ResourceNotFoundException, Exception {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        
        if(!"active".equals(user.getStatus())) {
            throw new Exception("El usuario no esta activo, con id :: " + id);
        }
        user.setStatus("inactive");
        
        final Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    /**
     * Agregar uun nuevo usuario
     * @param input Datos del nuevo usuario
     * @return Usuario creado
     */
    @PostMapping("/user")
    @Operation(summary = "Agregar un usuario", description = "Crear un usuario nuevo")
    public ResponseEntity<Users> post(@RequestBody Users input) {
        Users save = usersRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    /**
     * Generar datos de usuario aleatorios
     * @return Lista de usuarios generados
     */
    @PostMapping("/generate")
    @Operation(summary = "Generar usuarios", description = "Genera usuarios nuevos con datos aleatorios")
    public List<Users>  generate() {
        List<Users> users = this.generateUsers();
        return users;
    }
    
    /**
     * Marcar un usuario para borrado o eliminacion
     * @param id ID del usuario a marcar
     * @return Datos del usuario
     * @throws ResourceNotFoundException Excepcion cuando un objeto no existe
     * @throws Exception Manejo de excepciones
     */
    @DeleteMapping("/user/{id}")
    @Operation(summary = "Eliminar usuario", description = "Actualizar datos de un usuario por id marcandolo para eliminar ")
    public ResponseEntity<Users> delete(@PathVariable long id) throws ResourceNotFoundException, Exception {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + id));
        if("deleted".equals(user.getStatus())) {
            throw new Exception("El usuario ya ha sido marcado para eliminar, con id :: " + id);
        }
        user.setStatus("deleted");
        
        final Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    
    /**
     * Generar datos de usuarios aleatorios con faker
     * @return Lista de usuarios creados
     */
    public List<Users> generateUsers() {
        List<Users> users = new ArrayList<>();

        Faker faker = new Faker(new Locale("es-MX"));
        Users user;
        Random random = new Random();
        for (int i = 0; i < random.nextInt(100); i++) {
            user = new Users();
            user.setName(faker.name().firstName());
            user.setLastname(faker.name().lastName());
            user.setAddress(faker.address().streetAddress() + ", " + faker.address().cityName() + ", " + faker.address().country());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setStatus(faker.options().option("active", "inactive", "deleted"));
            user = usersRepository.save(user);
            users.add(user);
        }

        return users;

    }    
}
