package com.orangejuice.bank.dto;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class TransacaoRequest {
    private BigDecimal valor;
}

