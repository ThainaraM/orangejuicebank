package com.orangejuice.bank.controller;

import com.orangejuice.bank.dto.CriarContaDTO;
import com.orangejuice.bank.entity.Account;
import com.orangejuice.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> criarConta(@RequestBody CriarContaDTO dto) {
        Account novaConta = accountService.criarConta(dto);
        return ResponseEntity.ok(novaConta);
    }
}
