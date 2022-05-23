/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.repository;

import com.melichallenger.loans.entities.Targets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author biosx1706
 */
public interface TargetsRepository extends JpaRepository<Targets, Long> {
    
    @Query( value = "SELECT * FROM targets l "
        + "WHERE status = 'active' " 
        +   "AND (" 
        +       "(?1 BETWEEN cant_min AND cant_max AND (?2 BETWEEN amount_min AND amount_max)) "
        +   " OR " 
        +       "(cant_min > 0 AND cant_min < ?1 AND cant_max = 0 AND amount_min > 0 AND amount_min < ?2 AND amount_max = 0) "
        +   ")", nativeQuery = true )
    Targets findTarget(int cantLoans, Long amountRequest);
}
