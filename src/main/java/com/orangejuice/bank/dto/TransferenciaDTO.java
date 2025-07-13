package com.orangejuice.bank.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Getter
@Setter
@Data
public class TransferenciaDTO {
    private Long origemId;
    private Long destinoId;
    private Double valor;
}

