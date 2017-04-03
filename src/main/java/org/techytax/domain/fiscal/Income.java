package org.techytax.domain.fiscal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.domain.Cost;
import org.techytax.domain.Invoice;
import org.techytax.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import static java.math.BigInteger.ZERO;

@Component
public class Income {

  public BigInteger nettoOmzet = ZERO;

  @Autowired
  private InvoiceRepository invoiceRepository;

  public BigDecimal calculateNetIncome(String username) {
    Iterable<Invoice> invoices = invoiceRepository.findByUser(username);
    BigDecimal totalNetIncome = BigDecimal.ZERO;
    for (Invoice invoice : invoices) {
      totalNetIncome = totalNetIncome.add(BigDecimal.valueOf(invoice.getUnitsOfWork()).multiply(invoice.getProject().getRate()));
    }
    nettoOmzet = totalNetIncome.toBigInteger();
    return totalNetIncome;
  }
}