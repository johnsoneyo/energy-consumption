/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author johnson3yo
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "energy validation error occured.")
public class EnergyConsumptionException extends Exception {

    private String message;

    public EnergyConsumptionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    
}
