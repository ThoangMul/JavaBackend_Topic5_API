package com.example.demo.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id) {
        super("Not found with id: " + id);
    }
}
