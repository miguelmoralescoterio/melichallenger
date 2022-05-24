/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.melichallenger.resources;
import java.util.Date;

/**
 * Objeto para el control de respuestas de error
 * @author biosx1706
 */
public class ErrorDetails {
    private final Date timestamp;
    private final String message;
    private final String details;

    /**
     * Detalles del error
     * @param timestamp Fecha del error
     * @param message Mensaje de error
     * @param details detalles del error
     */
    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    /**
     * Obtener fecha del error
     * @return fecha del error
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Obtener mensaje del error
     * @return Mensaje de error
     */
    public String getMessage() {
        return message;
    }

    /**
     * Obtener detalles del error
     * @return Detalles del error
     */
    public String getDetails() {
        return details;
    }
}
