package com.orangejuice.bank.controller;

import com.orangejuice.bank.model.Conta;
import com.orangejuice.bank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public Conta criarConta(@RequestBody Map<String, Long> body) {
        Long usuarioId = body.get("usuarioId");
        return contaService.criarConta(usuarioId);
    }

    @GetMapping("/{id}")
    public Conta buscarConta(@PathVariable Long id) {
        return contaService.buscarContaPorId(id);
    }

    @GetMapping("/{id}/saldo")
    public BigDecimal consultarSaldo(@PathVariable Long id) {
        return contaService.consultarSaldo(id);
    }

   @PostMapping("/{id}/deposito")
    public Conta depositar(@PathVariable Long id, @RequestBody Map<String, BigDecimal> body) {
    return contaService.depositar(id, body.get("valor"));
}

    @PostMapping("/{id}/saque")
    public Conta sacar(@PathVariable Long id, @RequestBody Map<String, BigDecimal> body) {
        BigDecimal valor = body.get("valor");
        return contaService.sacar(id, valor);
    }

    @PostMapping("/transferencia")
    public void transferir(@RequestBody Map<String, String> body) {
        Long origemId = Long.parseLong(body.get("origemId"));
        Long destinoId = Long.parseLong(body.get("destinoId"));
        BigDecimal valor = new BigDecimal(body.get("valor"));

        contaService.transferir(origemId, destinoId, valor);
    }
}
