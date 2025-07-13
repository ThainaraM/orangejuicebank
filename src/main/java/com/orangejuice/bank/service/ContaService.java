package com.orangejuice.bank.service;

import com.orangejuice.bank.model.Conta;
import com.orangejuice.bank.model.Usuario;
import com.orangejuice.bank.repository.ContaRepository;
import com.orangejuice.bank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Conta criarConta(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Conta conta = new Conta(usuario.get());
        return contaRepository.save(conta);
    }

    public Conta buscarContaPorId(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public BigDecimal consultarSaldo(Long contaId) {
        Conta conta = buscarContaPorId(contaId);
        return conta.getSaldo();
    }

    public Conta depositar(Long contaId, BigDecimal valor) {
        Conta conta = buscarContaPorId(contaId);
        conta.setSaldo(conta.getSaldo().add(valor));
        return contaRepository.save(conta);
    }

    public Conta sacar(Long contaId, BigDecimal valor) {
        Conta conta = buscarContaPorId(contaId);
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        return contaRepository.save(conta);
    }

    public void transferir(Long origemId, Long destinoId, BigDecimal valor) {
        Conta origem = buscarContaPorId(origemId);
        Conta destino = buscarContaPorId(destinoId);

        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente na conta de origem");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));

        contaRepository.save(origem);
        contaRepository.save(destino);
    }
}

