package org.techytax.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class FiscalBalance {
	
	private BigDecimal totalPurchaseCost;
	
	private BigInteger beginSaldo;
	
	private BigInteger endSaldo;
	
	private BigInteger totalRemainingValue;

}
