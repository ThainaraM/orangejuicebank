package com.orangejuice.bank.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
@Getter
@Setter
@Data
public class SaqueDTO {
    private Long contaId;
    private Double valor;
}