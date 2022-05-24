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
 * repositorio de los prestamos 
 * @author biosx1706
 */
public interface LoansRepository extends JpaRepository<Loans, Long> {
    
    /**
     * Buscar prestamos por status
     * @param status
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l WHERE status = ?1", nativeQuery = true )
    List<Loans> findByStatus(String status);
    
    /**
     * Buscar prestamos por status con paginacion
     * @param status Status del prestamo
     * @param pageCount Registros de la pagina
     * @param page pagian a consultar
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l WHERE status = ?1 LIMIT ?2 OFFSET ?3 ", nativeQuery = true )
    List<Loans> findByStatus(String status, int pageCount, int page);
         
    /**
     * Buscar prestamos por status con paginacion desde una fecha 
     * @param status Status del prestamo
     * @param pageCount Registros de la pagina
     * @param page pagian a consultar
     * @param dateFrom Fecha desde 
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l WHERE status = ?1 AND DATE(created_at) >= ?4 LIMIT ?2 OFFSET ?3", nativeQuery = true )
    List<Loans> findByStatus(String status, int pageCount, int page, String dateFrom);
         
    /**
     * Buscar prestamos por status con paginacion desde una fecha 
     * @param status Status del prestamo
     * @param pageCount Registros de la pagina
     * @param page pagian a consultar
     * @param dateFrom Fecha desde 
     * @param dateTo Fecha hasta 
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l WHERE  status = ?1 AND DATE(created_at) BETWEEN ?4 AND ?5 LIMIT ?2 OFFSET ?3", nativeQuery = true )
    List<Loans> findByStatus(String status, int pageCount, int page, String dateFrom, String dateTo);
    
    /**
     * Buscar los prestamos activos de un usuario
     * @param user_id
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l WHERE status in('active', 'inactive') AND YEAR(created_at) = YEAR(CURRENT_TIME()) AND user_id = ?1", nativeQuery = true )
    List<Loans> findActiveLoansuser(Long user_id);
    
    /**
     * Buscar prestamos con paginacion
     * @param pageCount Cantidad de registros por pagina
     * @param page pagina a consultar
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l LIMIT ?1 OFFSET ?2 ", nativeQuery = true )
    List<Loans> findByPaginate(int pageCount, int page);
         
    /**
     * Buscar prestamos con paginacion desde una fecha
     * @param pageCount Cantidad de registros por pagina
     * @param page pagina a consultar
     * @param dateFrom Fecha desde
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l WHERE DATE(created_at) >= ?3 LIMIT ?1 OFFSET ?2", nativeQuery = true )
    List<Loans> findByPaginate(int pageCount, int page, String dateFrom);
         
    /**
     * Buscar prestamos con paginacion en un rango de fechas
     * @param pageCount Cantidad de registros por pagina
     * @param page pagina a consultar
     * @param dateFrom Fecha desde
     * @param dateTo Fecha hasta
     * @return Lista de prestamos
     */
    @Query( value = "SELECT * FROM loans l WHERE DATE(created_at) BETWEEN ?3 AND ?4 LIMIT ?1 OFFSET ?2", nativeQuery = true )
    List<Loans> findByPaginate(int pageCount, int page, String dateFrom, String dateTo);

    /**
     * Deuda total de los prestamos activos
     * @return Monto de la deuda
     */
    @Query( value = "SELECT COALESCE(SUM(amount_total), 0) AS debt_amount FROM loans l WHERE status = 'active'", nativeQuery = true )
    Double debtAmount();
    
    /**
     * Monto de la deuda total por target
     * @param target Target a consultar 
     * @return Monto de la deuda
     */
    @Query( value = "SELECT COALESCE(SUM(l.amount_total), 0) AS debt_amount "
            + "FROM loans l "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1)"
            + "WHERE l.status = 'active'", nativeQuery = true )
    Double debtAmountTarget(String target);

    /**
     * Monto de la deuda hasta uan fecha 
     * @param dateTo Fecha hasta donde se hace el calcculo
     * @return Monto de la deuda
     */
    @Query( value = "SELECT COALESCE(SUM(amount_total), 0) AS debt_amount FROM loans l WHERE status = 'active' AND DATE(created_at) <= ?1", nativeQuery = true )
    Double debtAmount(String dateTo);
    
    /**
     * Monto de la deuda por un target y hasta una fecha
     * @param target Target a consultar
     * @param dateTo Fecha hasta donde se hace el calcculo
     * @return Monto de la deuda
     */
    @Query( value = "SELECT COALESCE(SUM(l.amount_total), 0) AS debt_amount "
            + "FROM loans l "
            + "JOIN targets t ON t.id = l.taget_id AND UPPER(description) = UPPER(?1)"
            + "WHERE l.status = 'active' AND DATE(l.created_at) <= ?2", nativeQuery = true )
    Double debtAmountTarget(String target, String dateTo);
}
