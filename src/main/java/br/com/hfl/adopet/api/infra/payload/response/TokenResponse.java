package br.com.hfl.adopet.api.infra.payload.response;

import java.util.List;

public record TokenResponse(String accessToken, Long id, String login, String email, List<String> roles) {
}