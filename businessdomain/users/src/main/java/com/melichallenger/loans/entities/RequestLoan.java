/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.loans.entities;

import lombok.Data;

/**
 * Objeto para la solicitud de la deuda de los prestamos
 * @author biosx1706
 */
@Data
public class RequestLoan {
    private long amount;
    private int term;
    private long user_id;
}
