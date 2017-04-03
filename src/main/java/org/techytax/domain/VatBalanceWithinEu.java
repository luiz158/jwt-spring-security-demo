package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class VatBalanceWithinEu extends Balance {
	
	BigInteger turnoverNetEu;
	
	BigInteger vatOutEu;

}
