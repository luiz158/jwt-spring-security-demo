package org.techytax.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class Balance {

	BigDecimal brutoOmzet;

	BigDecimal correction;

	BigInteger nettoOmzet;

	BigDecimal totaleBaten;
	
	BigDecimal totaleKosten;

}
