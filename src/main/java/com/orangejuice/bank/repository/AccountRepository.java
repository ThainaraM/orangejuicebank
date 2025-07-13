package com.orangejuice.bank.repository;

import com.orangejuice.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}

