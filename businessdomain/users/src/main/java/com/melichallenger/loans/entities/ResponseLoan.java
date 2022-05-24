/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.entities;

import lombok.Data;

/**
 * Objeto para la respuesta al realizarse la solicitud de un prestamo
 * @author biosx1706
 */
@Data
public class ResponseLoan {
    private long loan_id;
    private double installment;
}
