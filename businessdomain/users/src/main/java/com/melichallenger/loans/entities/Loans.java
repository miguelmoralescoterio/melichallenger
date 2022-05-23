/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.melichallenger.users.entities.Users;
import com.melichallenger.loans.entities.Targets;
import com.melichallenger.loans.repository.TargetsRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;

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
    @JsonIgnore
    private long taget_id;
    @JsonIgnore
    private long user_id;
    private int term;
    @JsonIgnore
    private double amount_total;
    @JsonProperty("amount")
    private double amount_request;    
    private float rate;
    @JsonIgnore
    private double interest;
    @JsonIgnore
    private double installment;
    private String observation;
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

    public Loans() {        
    }
    
    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name="taget_id",referencedColumnName="id", insertable=false, updatable=false)
    private Targets target;    
    public String getTarget() {
        return this.target.getDescription();
    }
    
    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name="user_id",referencedColumnName="id", insertable=false, updatable=false)
    private Users user;
    public String getUser() {
        return this.user.getName() + " " + this.user.getLastname();
    }
}
