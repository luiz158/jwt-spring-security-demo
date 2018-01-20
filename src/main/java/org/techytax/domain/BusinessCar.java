package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("C")
@Getter
@Setter
public class BusinessCar extends Activum {

	private BigInteger fiscalIncomeAddition;

	/**
	 * Vindt het privégebruik plaats later dan 4 jaar na het jaar waarin u de auto in gebruik hebt genomen? Dan neemt u
	 * 1,5% in plaats van 2,7% van de catalogusprijs van de auto, inclusief btw en bpm.
	 */
	public BigInteger getVatCorrectionForPrivateUsage () {
    LocalDate currentDate = LocalDate.now();
    LocalDate firstCarUsageDate = getStartDate();
    BigDecimal correctionPercentage;
    if (currentDate.getYear() - firstCarUsageDate.getYear() < 6) {
    	correctionPercentage = new BigDecimal(.027);
		} else {
			correctionPercentage = new BigDecimal(.015);
		}
		return getPurchasePrice().multiply(correctionPercentage).setScale(0, BigDecimal.ROUND_DOWN).toBigInteger();
	}
	
}
