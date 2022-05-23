/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.melichallenger.loans.repository;

import com.melichallenger.loans.entities.Loans;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author biosx1706
 */
public interface LoansRepository extends JpaRepository<Loans, Long> {
    
    @Query( value = "SELECT * FROM loans l WHERE status = ?1", nativeQuery = true )
    List<Loans> findByStatus(String status);
    
    @Query( value = "SELECT * FROM loans l WHERE status = ?1 LIMIT ?2 OFFSET ?3 ", nativeQuery = true )
    List<Loans> findByStatus(String status, int pageCount, int page);
         
    @Query( value = "SELECT * FROM loans l WHERE status = ?1 AND DATE(created_at) >= ?4 LIMIT ?2 OFFSET ?3", nativeQuery = true )
    List<Loans> findByStatus(String status, int pageCount, int page, String dateFrom);
         
    @Query( value = "SELECT * FROM loans l WHERE  status = ?1 AND DATE(created_at) BETWEEN ?4 AND ?5 LIMIT ?2 OFFSET ?3", nativeQuery = true )
    List<Loans> findByStatus(String status, int pageCount, int page, String dateFrom, String dateTo);
    
    
    @Query( value = "SELECT * FROM loans l WHERE status in('active', 'inactive') AND YEAR(created_at) = YEAR(CURRENT_TIME()) AND user_id = ?1", nativeQuery = true )
    List<Loans> findActiveLoansuser(Long user_id);
    
    @Query( value = "SELECT * FROM loans l LIMIT ?1 OFFSET ?2 ", nativeQuery = true )
    List<Loans> findByPaginate(int pageCount, int page);
         
    @Query( value = "SELECT * FROM loans l WHERE DATE(created_at) >= ?3 LIMIT ?1 OFFSET ?2", nativeQuery = true )
    List<Loans> findByPaginate(int pageCount, int page, String dateFrom);
         
    @Query( value = "SELECT * FROM loans l WHERE DATE(created_at) BETWEEN ?3 AND ?4 LIMIT ?1 OFFSET ?2", nativeQuery = true )
    List<Loans> findByPaginate(int pageCount, int page, String dateFrom, String dateTo);

    @Query( value = "SELECT COALESCE(SUM(amount_total), 0) AS debt_amount FROM loans l WHERE status = 'active'", nativeQuery = true )
    Double debtAmount();
    
    @Query( value = "SELECT COALESCE(SUM(l.amount_total), 0) AS debt_amount "
            + "FROM loans l "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1)"
            + "WHERE l.status = 'active'", nativeQuery = true )
    Double debtAmountTarget(String target);

    @Query( value = "SELECT COALESCE(SUM(amount_total), 0) AS debt_amount FROM loans l WHERE status = 'active' AND DATE(created_at) <= ?1", nativeQuery = true )
    Double debtAmount(String dateTo);
    
    @Query( value = "SELECT COALESCE(SUM(l.amount_total), 0) AS debt_amount "
            + "FROM loans l "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1)"
            + "WHERE l.status = 'active' AND DATE(l.created_at) <= ?2", nativeQuery = true )
    Double debtAmountTarget(String target, String dateTo);
}
