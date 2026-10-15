package org.example.tp2.dto;

public record LoginRequest(
        String email,
        String password
) {
}