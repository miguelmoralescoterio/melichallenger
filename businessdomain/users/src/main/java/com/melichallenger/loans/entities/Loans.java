/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.entities;

import com.melichallenger.users.entities.Users;
import com.melichallenger.loans.entities.Targets;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author biosx1706
 */
@Entity
@Data
@Table(name="loans")
@ApiModel(description="Todos los detalles de los prestamos de los usuarios. ")
public class Loans implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private int taget_id;
    private int user_id;
    private int term;
    private long amount_total;
    private double installment;
    private String observation;
    private String status;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="DATETIME default CURRENT_TIMESTAMP")
    private final Timestamp created_at = null;
    
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false, columnDefinition="DATETIME default CURRENT_TIMESTAMP on update current_timestamp")
    private final Timestamp updated_at = null;
    @Column(name = "deleted_at", nullable = true)
    private final Timestamp deleted_at = null;

    public Loans() {        
    }
    
    public Loans(int taget_id, int user_id, int term, long amount_total, String observation) {        
        this.taget_id = taget_id;
        this.user_id = user_id;
        this.term = term;
        this.amount_total = amount_total;
        this.observation = observation;
        this.status = "active";
    }
}
