package br.com.hfl.adopet.api.controller;

import br.com.hfl.adopet.api.domain.usuario.Usuario;
import br.com.hfl.adopet.api.infra.payload.request.LoginRequest;
import br.com.hfl.adopet.api.infra.payload.response.TokenResponse;
import br.com.hfl.adopet.api.infra.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.login(), request.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        String jwt = jwtUtils.gerarToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        List<String> roles = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new TokenResponse(jwt,
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                roles));
    }
}