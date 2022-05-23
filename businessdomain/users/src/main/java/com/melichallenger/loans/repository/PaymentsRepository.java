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

    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid' AND loan_id = ?1 AND DATE(created_at) <= ?2", nativeQuery = true )
    Double paidAmount(Long loan_id, String dateTo);

    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid'", nativeQuery = true )
    Double paidAmount();

    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid' AND DATE(created_at) <= ?1", nativeQuery = true )
    Double paidAmount(String dateTo);

    @Query( value = "SELECT COALESCE(SUM(lp.amount), 0) AS paid_amount "
            + "FROM loans_payments lp "
            + "JOIN loans l ON l.id = lp.loan_id AND l.status = 'active' "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1) "
            + "WHERE lp.status = 'paid'", nativeQuery = true )
    Double paidAmountTarget(String target);

    @Query( value = "SELECT COALESCE(SUM(lp.amount), 0) AS paid_amount "
            + "FROM loans_payments lp "
            + "JOIN loans l ON l.id = lp.loan_id AND l.status = 'active' "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1) "
            + "WHERE lp.status = 'paid' AND DATE(lp.created_at) <= ?2", nativeQuery = true )
    Double paidAmountTarget(String target, String dateTo);
}
