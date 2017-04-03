package org.techytax.domain.fiscal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.cache.CostCache;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.Invoice;
import org.techytax.domain.PrivateWithdrawal;
import org.techytax.domain.VatBalanceWithinEu;
import org.techytax.helper.AmountHelper;
import org.techytax.repository.InvoiceRepository;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static java.math.BigInteger.ZERO;
import static org.techytax.domain.BalanceType.MACHINERY;
import static org.techytax.domain.CostConstants.FOR_PERCENTAGE;
import static org.techytax.domain.CostConstants.MAXIMALE_FOR;
import static org.techytax.helper.AmountHelper.roundToInteger;

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

  public BigInteger getWinst() {
    return profit;
  }

  public void handleProfitAndLoss(PrivateWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts, String username) throws Exception {
    handleTurnOver(username);

    companyCosts.calculate(username);
    financialIncomeAndExpenses.calculate(username);
    depreciation.handleDepreciations(username);

    handleCar(privatWithdrawal, deductableCosts);

//    handleDepreciations();


//    calculateProfit();
//
//    handleInvestments();
  }

  private void handleTurnOver(String username) throws Exception {
//    income = new Income();
    income.calculateNetIncome(username);

  }

  private void handleCar(PrivateWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts) throws Exception {
    BigDecimal afschrijvingAuto = BigDecimal.ZERO; // TODO
//    BookValue carActivum = bookRepository.getBookValue(BalanceType.CURRENT_ASSETS, bookYear);
//    if (carActivum != null) {
//      handleBusinessCar(privatWithdrawal, deductableCosts, afschrijvingAuto);
//    } else {
//      handlePrivateCar(deductableCosts);
//    }
  }

//  private void handleBusinessCar(PrivateWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts, BigDecimal afschrijvingAuto) throws Exception {
//    if (afschrijvingAuto != null) {
//      overview.setAfschrijvingAuto(AmountHelper.roundToInteger(afschrijvingAuto));
//    }
//    // TODO: fiscale bijtelling laten invoeren
//    overview.setBijtellingAuto(roundToInteger(balanceCalculator.getFiscaleBijtelling(deductableCosts)));
//    overview.setKostenAuto(roundToInteger(balanceCalculator.getKostenVoorAuto(deductableCosts)));
//    handleDepreciationCorrections();
//    BigInteger kostenAutoAftrekbaar = overview.getBijtellingAuto();
//    kostenAutoAftrekbaar = kostenAutoAftrekbaar.subtract(overview.getKostenAuto());
//    if (overview.getAfschrijvingAuto() != null) {
//      kostenAutoAftrekbaar = kostenAutoAftrekbaar.subtract(overview.getAfschrijvingAuto());
//    }
//    if (kostenAutoAftrekbaar.compareTo(BigInteger.ZERO) == 1) {
//      kostenAutoAftrekbaar = BigInteger.ZERO;
//    }
//    handleBusinessCarPrivateWithDrawal(privatWithdrawal, kostenAutoAftrekbaar);
//    overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
//  }
//
//  private void handlePrivateCar(List<DeductableCostGroup> deductableCosts) {
//    overview.setKostenAuto(roundToInteger(balanceCalculator.getKostenVoorAuto(deductableCosts)));
//    BigInteger kostenAutoAftrekbaar = BigInteger.ZERO;
//    kostenAutoAftrekbaar = kostenAutoAftrekbaar.subtract(overview.getKostenAuto());
//    overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
//  }
//
//  public void calculateProfit() {
//    nettoOmzet = nettoOmzet.add(overview.getInterestFromBusinessSavings());
//    nettoOmzet = nettoOmzet.subtract(overview.getRepurchase());
//    nettoOmzet = nettoOmzet.add(overview.getKostenAutoAftrekbaar());
//    nettoOmzet = nettoOmzet.subtract(overview.getKostenOverigTransport());
//    nettoOmzet = nettoOmzet.subtract(overview.getKostenOverig());
//    nettoOmzet = nettoOmzet.subtract(overview.getSettlementCosts());
//    nettoOmzet = nettoOmzet.subtract(overview.getAfschrijvingTotaal());
//    overview.setProfit(nettoOmzet);
//  }
//
//  private void handleInvestments() throws Exception {
//    List<Cost> investmentKostList = costCache.getInvestments();
//    overview.setInvestmentDeduction(investmentDeductionHelper.getInvestmentDeduction(investmentKostList));
//  }


}
