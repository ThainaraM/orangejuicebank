package com.orangejuice.bank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoConta;
    private String numeroConta;
    private String email;
    private Double saldo = 0.0;
}