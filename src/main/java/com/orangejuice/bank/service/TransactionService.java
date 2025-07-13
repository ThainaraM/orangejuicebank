package com.orangejuice.bank.service;

import com.orangejuice.bank.entity.Account;
import com.orangejuice.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
 public class TransactionService {

    private final AccountRepository accountRepository;

    public Account depositar(Long contaId, Double valor) {
        Account conta = accountRepository.findById(contaId)
            .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }

        conta.setSaldo(conta.getSaldo() + valor);
        return accountRepository.save(conta);
    }

    public Account sacar(Long contaId, Double valor) {
        Account conta = accountRepository.findById(contaId)
            .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }

        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo() - valor);
        return accountRepository.save(conta);
    }
}
