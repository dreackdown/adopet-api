package com.example.adopet.api.dto.Tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record DadosCadastroTutor(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "O telefone é obrigatório")
        @Length(min = 10, max = 11, message = "O telefone deve conter 11 dígitos")
        @Pattern(regexp = "\\d{10,11}", message = "Para telefone fixo 10 dígitos, para telefone móvel 11 dígitos")
        String telefone,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "A descrição sobre é obrigatória")
        String sobre,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String senha,

        String imageUrl
) {
}
