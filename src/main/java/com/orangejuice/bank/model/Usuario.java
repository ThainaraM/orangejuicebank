package com.orangejuice.bank.model;

import javax.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

@Entity
public class Usuario {
    // Add fields, constructors, getters, and setters here as needed
}

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCpf(String cpf);
}