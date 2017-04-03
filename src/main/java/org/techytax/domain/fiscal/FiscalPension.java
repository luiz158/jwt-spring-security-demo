package org.techytax.domain.fiscal;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.techytax.domain.CostConstants.FOR_PERCENTAGE;
import static org.techytax.domain.CostConstants.MAXIMALE_FOR;

@Data
public class FiscalPension {
  private BigInteger maximalFiscalPension;

  // TODO: De toevoeging is maximaal het bedrag waarmee het ondernemingsvermogen aan het einde van het kalenderjaar uitkomt boven de oudedagsreserve aan het begin van het kalenderjaar.
  private void setMaximalFiscalPension(BigInteger profit) {
    BigInteger maximaleFor = new BigDecimal(profit).multiply(FOR_PERCENTAGE).toBigInteger();
    if (maximaleFor.compareTo(MAXIMALE_FOR) == 1) {
      maximaleFor = MAXIMALE_FOR;
    }
    if (maximaleFor.compareTo(BigInteger.ZERO) == -1) {
      maximaleFor = BigInteger.ZERO;
    }
    maximalFiscalPension = maximaleFor;
  }
}
