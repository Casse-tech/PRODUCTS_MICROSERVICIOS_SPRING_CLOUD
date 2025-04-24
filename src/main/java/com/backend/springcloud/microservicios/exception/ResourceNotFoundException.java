package com.backend.springcloud.microservicios.exception;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
