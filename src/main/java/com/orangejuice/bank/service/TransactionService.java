package com.orangejuice.bank.service;

import com.orangejuice.bank.dto.DepositoDTO;
import com.orangejuice.bank.entity.Account;
import com.orangejuice.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    public Account depositar(DepositoDTO dto) {
        Account conta = accountRepository.findById(dto.getContaId())
            .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        if (dto.getValor() <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }

        conta.setSaldo(conta.getSaldo() + dto.getValor());
        return accountRepository.save(conta);
    }
}
