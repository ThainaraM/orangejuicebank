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

        conta.setSaldo(conta.getSaldo() + valor);
        return accountRepository.save(conta);
    }

    public Account sacar(Long contaId, Double valor) {
        Account conta = accountRepository.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo() - valor);
        return accountRepository.save(conta);
    }

    public void transferir(Long origemId, Long destinoId, Double valor) {
        Account origem = accountRepository.findById(origemId)
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada"));

        Account destino = accountRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada"));

        if (origem.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para transferência");
        }

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        accountRepository.save(origem);
        accountRepository.save(destino);
    }
}

