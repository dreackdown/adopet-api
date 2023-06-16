package com.example.adopet.api.controller;

import com.example.adopet.api.domain.usuario.LoginRequest;
import com.example.adopet.api.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final TokenService jwtService;

    public LoginController(AuthenticationManager authenticationManager, TokenService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest request) {
        var dadosLoginSpring = new UsernamePasswordAuthenticationToken(request.login(), request.senha());
        var authentication = authenticationManager.authenticate(dadosLoginSpring);
        var jwt = jwtService.gerarToken(authentication);

        return ResponseEntity.ok(jwt);
    }
}