package com.orangejuice.bank.exception;

public class ContaNaoEncontradaException extends RuntimeException {

    public ContaNaoEncontradaException(Long id) {
        super("Conta não encontrada: " + id);
    }
}
