package org.techytax.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.cache.CostCache;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.FiscalBalance;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivateWithdrawal;
import org.techytax.domain.fiscal.FiscalOverview;
import org.techytax.domain.fiscal.ProfitAndLoss;
import org.techytax.repository.ActivumRepository;
import org.techytax.repository.BookRepository;
import org.techytax.repository.CostRepository;
import org.techytax.util.DateHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.techytax.domain.BalanceType.VAT_TO_BE_PAID;

@Component
public class FiscalOverviewHelper {
	@Autowired
	private CostCache costCache;

	@Autowired
	private ActivumRepository activumDao;
	
	@Autowired
	private BookRepository bookValueDao;
	
	@Autowired
	private CostRepository costDao;
	
	@Autowired
	private ActivaHelper activaHelper;
	
	private FiscalOverview overview;

	@Autowired
	private ProfitAndLoss profitAndLoss;

	@Autowired
	private InvestmentDeductionHelper investmentDeductionHelper;
	
	private int bookYear;
	private Map<BalanceType, FiscalBalance> passivaMap;
	
	@Autowired
	private BalanceCalculator balanceCalculator;

	public FiscalOverview createFiscalOverview(Date beginDatum, Date eindDatum, String username) throws Exception {
		bookYear = DateHelper.getFiscalYear();
		overview = new FiscalOverview();
		passivaMap = new HashMap<>();
		PrivateWithdrawal privatWithdrawal = new PrivateWithdrawal();

		List<DeductableCostGroup> deductableCosts = null; // = costCache.getDeductableCosts();
		overview.setJaar(bookYear);

//		overview.getProfitAndLoss().getDepreciation().handleDepreciations();

		activaHelper.setFiscalOverview(overview);
		activaHelper.setCostCache(costCache);
		Map<BalanceType, FiscalBalance> activaMap = activaHelper.handleActiva(costCache.getBusinessAccountCosts(), overview);
		overview.setActivaMap(activaMap);

//		List<BookValue> activumListThisYear = bookValueDao.getActivaList(bookYear);
//		List<BookValue> activumListPreviousYear = bookValueDao.getActivaList(bookYear - 1);
//		setBalanceTotals(activumListThisYear, activumListPreviousYear);

//    ProfitAndLoss profitAndLoss = new ProfitAndLoss();
    profitAndLoss.handleProfitAndLoss(privatWithdrawal, deductableCosts, username);
    overview.setProfitAndLoss(profitAndLoss);

		handlePassiva();
		//    setMaximalFiscalPension();
		overview.setPassivaMap(passivaMap);

//		List<BookValue> passivaList = bookValueDao.getPassivaList(bookYear);

//		BigInteger enterpriseCapital = getEnterpriseCapital(passivaList);
//		overview.setEnterpriseCapital(enterpriseCapital);

		BigDecimal privateDeposit = handlePrivateDeposits();

//		handlePrivateWithdrawals(privatWithdrawal, enterpriseCapital, privateDeposit);

		handlePrepaidTaxes();

		return overview;
	}

	private void setBalanceTotals(List<BookValue> activumListThisYear, List<BookValue> activumListPreviousYear) {
		BigInteger bookTotalBegin = getBalansTotaal(activumListPreviousYear);
		BigInteger bookTotalEnd = getBalansTotaal(activumListThisYear);
		overview.setBookTotalBegin(bookTotalBegin);
		overview.setBookTotalEnd(bookTotalEnd);

	}

	private void handlePassiva() throws Exception {

//		BigInteger fiscalPension = handleFiscalPension();
//
//		BigDecimal vatDebt = handleVatToBePaid();
//
//		handleNonCurrentAssets(fiscalPension, vatDebt);
	}

	private void handleBusinessCarPrivateWithDrawal(PrivateWithdrawal privatWithdrawal, BigInteger kostenAutoAftrekbaar) {
		BigInteger withDrawal = BigInteger.ZERO;
//		if (kostenAutoAftrekbaar.compareTo(BigInteger.ZERO) == -1) {
//			withDrawal = overview.getBijtellingAuto();
//			privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(withDrawal);
//		} else {
//			withDrawal = overview.getKostenAuto();
//			withDrawal.add(overview.getAfschrijvingAuto());
//			privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(withDrawal);
//		}
	}


	private BigDecimal handlePrivateDeposits() throws Exception {
		BigDecimal privateDeposit = BigDecimal.ZERO; //  costCache.getCostsWithPrivateMoney();
		overview.setPrivateDeposit(privateDeposit.toBigInteger());
		return privateDeposit;
	}

	private void handlePrepaidTaxes() throws Exception {
		PrepaidTax prepaidTax = new PrepaidTax(); // costCache.getPrepaidTax();
		overview.setPrepaidTax(prepaidTax);
	}

	private void handlePrivateWithdrawals(PrivateWithdrawal privateWithdrawal, BigInteger enterpriseCapital, BigDecimal privateDeposit) throws Exception {
//		List<BookValue> passivaListPreviousYear = bookValueDao.getPassivaList(bookYear - 1);
//		BigInteger enterpriseCapitalPreviousYear = getEnterpriseCapital(passivaListPreviousYear);
//        overview.setEnterpriseCapitalPreviousYear(enterpriseCapitalPreviousYear);
//		BigInteger totalWithdrawal = overview.getProfit();
//		totalWithdrawal = totalWithdrawal.subtract(enterpriseCapital.subtract(enterpriseCapitalPreviousYear));
//		totalWithdrawal = totalWithdrawal.add(roundDownToInteger(privateDeposit));
//		privateWithdrawal.setTotaleOnttrekking(totalWithdrawal);
//		BigInteger withdrawalCash = totalWithdrawal;
//		if (privateWithdrawal.getWithdrawalPrivateUsageBusinessCar() != null) {
//			withdrawalCash = withdrawalCash.subtract(privateWithdrawal.getWithdrawalPrivateUsageBusinessCar());
//		}
//		privateWithdrawal.setWithdrawalCash(withdrawalCash);
//		overview.setOnttrekking(privateWithdrawal);
	}

	private BigInteger getEnterpriseCapital(List<BookValue> passiva) {
		BigInteger enterpriseCapital = BigInteger.ZERO;
		for (BookValue passivum : passiva) {
			if (passivum.getBalanceType() != VAT_TO_BE_PAID) {
				enterpriseCapital = enterpriseCapital.add(passivum.getSaldo());
			}
		}
		return enterpriseCapital;
	}

	private BigInteger getBalansTotaal(List<BookValue> activaLijst) {
		Iterator<BookValue> iterator = activaLijst.iterator();
		BigInteger totaal = BigInteger.ZERO;
		while (iterator.hasNext()) {
			BookValue activa = iterator.next();
			totaal = totaal.add(activa.getSaldo());
		}
		return totaal;
	}
}
