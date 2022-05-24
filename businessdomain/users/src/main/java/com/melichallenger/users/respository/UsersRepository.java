/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.melichallenger.users.respository;

import com.melichallenger.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para las consultas de Usuarios
 * @author biosx1706
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
