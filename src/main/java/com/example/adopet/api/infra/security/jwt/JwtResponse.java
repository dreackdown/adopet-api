package com.example.adopet.api.infra.security.jwt;

import java.util.List;

public record JwtResponse(String accessToken, Long id, String login, String email, List<String> roles) {
}