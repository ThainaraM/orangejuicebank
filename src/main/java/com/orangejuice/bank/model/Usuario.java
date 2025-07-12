package com.orangejuice.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    private BigDecimal saldo;

    private String senha;

    private String numeroConta;
}
