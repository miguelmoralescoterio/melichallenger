/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.repository;

import com.melichallenger.loans.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author biosx1706
 */
public interface PaymentsRepository extends JpaRepository<Payments, Long>{
    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid' AND loan_id = ?1", nativeQuery = true )
    Double paidAmount(Long loan_id);
}
