package com.example.adopet.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/tutor")
    @PreAuthorize("hasRole('TUTOR')")
    public String moderatorAccess() {
        return "Tutor Board.";
    }

    @GetMapping("/abrigo")
    @PreAuthorize("hasRole('ABRIGO')")
    public String adminAccess() {
        return "Abrigo Board.";
    }
}