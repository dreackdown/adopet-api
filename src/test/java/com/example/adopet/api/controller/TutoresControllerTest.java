package com.example.adopet.api.controller;

import com.example.adopet.api.dto.Tutor.DadosCadastroTutor;
import com.example.adopet.api.dto.Tutor.DadosDetalhesTutor;
import com.example.adopet.api.entities.Tutor;
import com.example.adopet.api.repositories.TutorRepository;
import com.example.adopet.api.services.TutorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;

@SpringBootTest
@AutoConfigureMockMvc
class TutoresControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    TutorService tutorService;

    @MockBean
    TutorRepository tutorRepository;

    @Test
    public void tutoresTestGetAll() throws Exception {
        List<DadosDetalhesTutor> tutores = List.of(new DadosDetalhesTutor(1L, "Hugo Faria Lima", "hugofaria@google.com"));
        when(tutorService.findAll()).thenReturn(tutores);
        mockMvc.perform(get("/tutores"))
                .andExpect(status().isOk());
    }

    @Test
    public void tutoresTestSave() throws Exception {
        var tutorDTO = new DadosCadastroTutor("Juliet Patton", "sodales@icloud.couk", "VRT88DYD3LJ", "VRT88DYD3LJ");
        when(tutorService.save(any())).thenReturn(new Tutor(tutorDTO));
        mockMvc.perform(post("/tutores")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(tutorDTO)))
                .andExpect(status().isCreated());
    }
}