package com.orangejuice.bank.controller;

import com.orangejuice.bank.dto.DepositoDTO;
import com.orangejuice.bank.entity.Account;
import com.orangejuice.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposito")
    public ResponseEntity<Account> depositar(@RequestBody DepositoDTO dto) {
        Account contaAtualizada = transactionService.depositar(dto);
        return ResponseEntity.ok(contaAtualizada);
    }
}
