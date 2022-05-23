/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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
@Table(name="targets")
@ApiModel(description="Todos los targets definidos. ")
public class Targets implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String description;
    private long amount_min;
    private long amount_max;
    private int cant_max;
    private int cant_min;
    private float rate;
    private String status;
        
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="DATETIME default CURRENT_TIMESTAMP")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Timestamp created_at = null;
    
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false, columnDefinition="DATETIME default CURRENT_TIMESTAMP on update current_timestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Timestamp updated_at = null;
    
    @Column(name = "deleted_at", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Timestamp deleted_at = null;

    public Targets() {        
    }
    
    public Targets(String description, long amount_min, long amount_max, int cant_min, int cant_max, float rate) {        
        this.description = description;
        this.amount_min = amount_min;
        this.amount_max = amount_max;
        this.cant_max = cant_max;
        this.cant_min = cant_min;
        this.rate = rate;
        this.status = "active";
    }    
}
