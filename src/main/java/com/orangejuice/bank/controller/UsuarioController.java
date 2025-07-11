package com.orangejuice.bank.controller;

import com.orangejuice.bank.model.Usuario;
import com.orangejuice.bank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        usuario.setSaldo(BigDecimal.ZERO);
        usuario.setNumeroConta(gerarNumeroConta());
        return usuarioRepository.save(usuario);
    }

    private String gerarNumeroConta() {
        int parte1 = (int)(Math.random() * 90000) + 10000;
        int parte2 = (int)(Math.random() * 9) + 1;
        return parte1 + "-" + parte2;
    }
}

