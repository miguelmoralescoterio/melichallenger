/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.entities;

import lombok.Data;

/**
 *
 * @author biosx1706
 */
@Data
public class ResponsePayment {
    private long id;
    private double amount;
    private double debt;
    private long loan_id;
}
