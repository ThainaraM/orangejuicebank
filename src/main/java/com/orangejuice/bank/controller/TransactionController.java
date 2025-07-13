package com.orangejuice.bank.controller;
import com.orangejuice.bank.service.TransactionService;
import com.orangejuice.bank.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor

public class TransactionController {
private final TransactionService transactionService;

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Account> depositar(@PathVariable Long id, @RequestBody Double valor) {
        Account contaAtualizada = transactionService.depositar(id, valor);
        return ResponseEntity.ok(contaAtualizada);
    }

    @PostMapping("/{id}/sacar")
    public ResponseEntity<Account> sacar(@PathVariable Long id, @RequestBody Double valor) {
        Account contaAtualizada = transactionService.sacar(id, valor);
        return ResponseEntity.ok(contaAtualizada);
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }
}
