/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.melichallenger.loans.repository;

import com.melichallenger.loans.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author biosx1706
 */
public interface LoansRepository extends JpaRepository<Loans, Long> {
    
}
