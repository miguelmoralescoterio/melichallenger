/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.repository;

import com.melichallenger.loans.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositorio para consultas de los pagos
 * @author biosx1706
 */
public interface PaymentsRepository extends JpaRepository<Payments, Long>{

    /**
     * Monto de pagos por prestamo
     * @param loan_id ID del prestamo
     * @return Monto de pagos
     */
    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid' AND loan_id = ?1", nativeQuery = true )
    Double paidAmount(Long loan_id);

    /**
     * Monto de pagos por prestamo hasta una fecha
     * @param loan_id ID del prestamo
     * @param dateTo Fecha hasta
     * @return Monto de pagos
     */
    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid' AND loan_id = ?1 AND DATE(created_at) <= ?2", nativeQuery = true )
    Double paidAmount(Long loan_id, String dateTo);

    /**
     * Monto total de todos los pagos
     * @return Monto de pagos
     */
    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid'", nativeQuery = true )
    Double paidAmount();

    /**
     * Monto total de todos los pagos hasta una fecha
     * @param dateTo Fecha hasta
     * @return Monto de pagos
     */
    @Query( value = "SELECT COALESCE(SUM(amount), 0) AS paid_amount FROM loans_payments l WHERE status = 'paid' AND DATE(created_at) <= ?1", nativeQuery = true )
    Double paidAmount(String dateTo);

    /**
     * Monto total de todos los pagos por target
     * @param target
     * @return Monto de pagos
     */
    @Query( value = "SELECT COALESCE(SUM(lp.amount), 0) AS paid_amount "
            + "FROM loans_payments lp "
            + "JOIN loans l ON l.id = lp.loan_id AND l.status = 'active' "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1) "
            + "WHERE lp.status = 'paid'", nativeQuery = true )
    Double paidAmountTarget(String target);

    /**
     * Monto total de todos los pagos por target hasta uan fecha
     * @param target Target a consultar
     * @param dateTo Fecha hasta
     * @return Monto de pagos
     */
    @Query( value = "SELECT COALESCE(SUM(lp.amount), 0) AS paid_amount "
            + "FROM loans_payments lp "
            + "JOIN loans l ON l.id = lp.loan_id AND l.status = 'active' "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1) "
            + "WHERE lp.status = 'paid' AND DATE(lp.created_at) <= ?2", nativeQuery = true )
    Double paidAmountTarget(String target, String dateTo);
}
