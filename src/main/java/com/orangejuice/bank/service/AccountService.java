package com.orangejuice.bank.service;

import com.orangejuice.bank.dto.CriarContaDTO;
import com.orangejuice.bank.entity.Account;
import com.orangejuice.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
 private final AccountRepository accountRepository;

    public Account criarConta(CriarContaDTO dto) {
        if (accountRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (accountRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        Account novaConta = Account.builder()
            .nome(dto.getNome())
            .email(dto.getEmail())
            .cpf(dto.getCpf())
            .saldo(0.0)
            .build();

        return accountRepository.save(novaConta);
    }
}

