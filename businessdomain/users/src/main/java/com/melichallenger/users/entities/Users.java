/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.users.entities;

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
@Table(name="users")
@ApiModel(description="Todos los detalles de los usuarios. ")
public class Users implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @ApiModelProperty(notes = "Nombre(s) del Usuario", example = "Miguel", required = true)
    private String name;
    @ApiModelProperty(notes = "Apellido(s) del Usuario", example = "Morales", required = true)
    private String lastname;
    private String phone;
    private String address;
    private String status;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition="DATETIME default CURRENT_TIMESTAMP")
    private final Timestamp created_at = null;
    
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false, columnDefinition="DATETIME default CURRENT_TIMESTAMP on update current_timestamp")
    private final Timestamp updated_at = null;
    @Column(name = "deleted_at", nullable = true)
    private final Timestamp deleted_at = null;

    public Users() {        
    }
    
    public Users(String name, String lastname, String phone, String address) {        
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.status = "active";
    }
}
