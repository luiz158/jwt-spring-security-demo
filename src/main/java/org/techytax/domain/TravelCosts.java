package org.techytax.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TravelCosts {
	
	private BigDecimal autoKostenMetBtw;
	
	private BigDecimal autoKostenZonderBtw;
	
	private BigDecimal ovKosten;
	
	private BigDecimal vatCorrection;

}
