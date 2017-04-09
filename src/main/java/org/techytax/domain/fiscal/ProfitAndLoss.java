package org.techytax.domain.fiscal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.domain.PrivateWithdrawal;
import org.techytax.repository.InvoiceRepository;

import java.math.BigInteger;

import static java.math.BigInteger.ZERO;

@Data
@Component
public class ProfitAndLoss {

  @Autowired
  @JsonIgnore
  InvoiceRepository invoiceRepository;

  @Autowired
  private Income income;

  private BigInteger repurchase = ZERO;

  @Autowired
  private Depreciation depreciation;

  @Autowired
  private CompanyCosts companyCosts;

  @Autowired
  private FinancialIncomeAndExpenses financialIncomeAndExpenses;

  @Autowired
  private ExtraordinaryIncomeAndExpenses extraordinaryIncomeAndExpenses;

  private BigInteger profit = ZERO;

  public void handleProfitAndLoss(PrivateWithdrawal privatWithdrawal, String username) throws Exception {
    handleTurnOver(username);

    companyCosts.calculate(username);
    financialIncomeAndExpenses.calculate(username);
    depreciation.handleDepreciations(username);
  }

  private void handleTurnOver(String username) throws Exception {
    income.calculateNetIncome(username);
  }
}
