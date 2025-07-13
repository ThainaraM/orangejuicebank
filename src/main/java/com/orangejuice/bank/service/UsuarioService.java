package com.orangejuice.bank.service;

import com.orangejuice.bank.model.Usuario;
import com.orangejuice.bank.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario criarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getCpf() == null) {
            throw new IllegalArgumentException("Email e CPF são obrigatórios.");
        }

        if (repository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        if (repository.existsByCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    public Usuario depositar(Long id, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }

        Usuario usuario = buscarPorId(id);
        usuario.setSaldo(usuario.getSaldo().add(valor));
        return repository.save(usuario);
    }

    public Usuario sacar(Long id, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }

        Usuario usuario = buscarPorId(id);

        if (usuario.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        usuario.setSaldo(usuario.getSaldo().subtract(valor));
        return repository.save(usuario);
    }
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
   

}