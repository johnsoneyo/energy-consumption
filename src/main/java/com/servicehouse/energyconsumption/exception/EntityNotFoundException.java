/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.exception;

/**
 *
 * @author johnson3yo
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find entity with id.")
public class EntityNotFoundException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public EntityNotFoundException(String message)
    {
        super(message);
    }

}
