/**
 * Copyright 2014 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.helper;

import org.techytax.domain.Activum;
import org.techytax.util.DateHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;

public class DepreciationHelper {

	/* Belastingdienst: De afschrijving is per jaar maximaal 20% op de aanschafkosten van het bedrijfsmiddel.
	   Voor goodwill geldt een percentage van maximaal 10%.
	 */
	private static final int MINIMUM_NUMBER_OF_DEPRECIATION_YEARS = 5;

	public BigInteger getDepreciation(Activum activum) {
		BigDecimal yearlyDepreciation = BigDecimal.ZERO;
		int fiscalYear = DateHelper.getFiscalYear();
		int years = activum.getNofYearsForDepreciation();
		if ((years > 0 && activum.getEndDate() == null && (isYearForDeprecation(activum.getStartDate(), years)))) {
			BigDecimal maximumYearlyDepreciation = activum.getCost().getAmount().
					subtract(BigDecimal.valueOf(activum.getRemainingValue().intValue())).
					divide(BigDecimal.valueOf(MINIMUM_NUMBER_OF_DEPRECIATION_YEARS), 2,
					RoundingMode.HALF_UP);
			yearlyDepreciation = activum.getCost().getAmount().subtract(BigDecimal.valueOf(activum.getRemainingValue().intValue())).divide(BigDecimal.valueOf(years), 2,
					RoundingMode.HALF_UP);
			double proportion = 1.0d;
			if (fiscalYear == years + activum.getStartDate().getYear()) {
				proportion = activum.getStartDate().getMonthValue() / 12.0d;
			} else if (fiscalYear == activum.getStartDate().getYear()) {
				proportion = 1.0d - activum.getStartDate().getMonthValue() / 12.0d;
			} 
			yearlyDepreciation = yearlyDepreciation.multiply(BigDecimal.valueOf(proportion));
			if (yearlyDepreciation.compareTo(maximumYearlyDepreciation) == 1) {
				yearlyDepreciation = maximumYearlyDepreciation;
			}
		}
		return AmountHelper.roundToInteger(yearlyDepreciation);
	}

	private boolean isYearForDeprecation(LocalDate startDate, int years) {
		if (startDate.getMonth().getValue() > 0) {
			years++;
		}
		return DateHelper.getFiscalYear() >= startDate.getYear() || DateHelper.getFiscalYear() > years + startDate.getYear();
	}

}
