/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.melichallenger.respository;

import com.melichallenger.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author biosx1706
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
