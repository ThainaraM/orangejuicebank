package com.orangejuice.bank.controller;
import com.orangejuice.bank.service.TransactionService;
import com.orangejuice.bank.dto.DepositoDTO;
import com.orangejuice.bank.dto.SaqueDTO;
import com.orangejuice.bank.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor

public class TransactionController {
private final TransactionService transactionService;

   @PostMapping("/depositar")
public ResponseEntity<Account> depositar(@RequestBody DepositoDTO dto) {
    Account contaAtualizada = transactionService.depositar(dto.getContaId(), dto.getValor());
    return ResponseEntity.ok(contaAtualizada);
}

@PostMapping("/sacar")
public ResponseEntity<Account> sacar(@RequestBody SaqueDTO dto) {
    Account contaAtualizada = transactionService.sacar(dto.getContaId(), dto.getValor());
    return ResponseEntity.ok(contaAtualizada);
 }
  
}
