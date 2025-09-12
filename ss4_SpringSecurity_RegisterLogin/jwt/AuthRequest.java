package com.example.demo.jwt;

public record AuthRequest(
        String username,
        String password
) {}
