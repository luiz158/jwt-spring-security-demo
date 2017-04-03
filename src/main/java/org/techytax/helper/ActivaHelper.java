package org.techytax.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.cache.CostCache;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Cost;
import org.techytax.domain.FiscalBalance;
import org.techytax.domain.fiscal.FiscalOverview;
import org.techytax.repository.ActivumRepository;
import org.techytax.repository.BookRepository;
import org.techytax.util.DateHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.techytax.domain.BalanceType.MACHINERY;

@Component
public class ActivaHelper {

    public FiscalOverview getFiscalOverview() {
		return fiscalOverview;
	}

	public void setFiscalOverview(FiscalOverview fiscalOverview) {
		this.fiscalOverview = fiscalOverview;
	}

	public CostCache getCostCache() {
		return costCache;
	}

	public void setCostCache(CostCache costCache) {
		this.costCache = costCache;
	}

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ActivumRepository activumRepository;
	
	private FiscalOverview fiscalOverview;
	private CostCache costCache;
	private int bookYear;
	private Map<BalanceType, FiscalBalance> activaMap;
	
	@Autowired
	private BalanceCalculator balanceCalculator;

	public Map<BalanceType, FiscalBalance> handleActiva(List<Cost> rekeningLijst, FiscalOverview fiscalOverview) throws Exception {
		bookYear = DateHelper.getYear(new Date()) - 1;
		activaMap = new HashMap<>();

//		handleCurrentAssets(rekeningLijst);
//
		handleMachinery("");
//
//		handleSettlement(fiscalOverview);
//
//		handleBusinessCar();

//		handleStock();

//		handleInvoicesToBePaid();

		return activaMap;

	}

//	public List<Activum> getActiveActiva() throws Exception {
//		return activumDao.getActiveActiva();
//	}

	private void handleInvoicesToBePaid() throws Exception {
//		BookValue currentBookValue = bookRepository.getBookValue(INVOICES_TO_BE_PAID, bookYear);
//		BookValue previousBookValue = bookRepository.getBookValue(INVOICES_TO_BE_PAID, bookYear - 1);
//
//		BigDecimal turnOverUnpaid = fiscalOverview.getTurnOverUnpaid();
//		if (turnOverUnpaid != null && turnOverUnpaid.compareTo(BigDecimal.ZERO) != 0) {
//			BigInteger saldo = AmountHelper.roundToInteger(turnOverUnpaid);
//			BookValue newBookValue = new BookValue(INVOICES_TO_BE_PAID, bookYear, saldo);
//			newBookValue.setUser(UserCredentialManager.getUser());
//
//			if (currentBookValue == null) {
//				bookRepository.persistEntity(newBookValue);
//			} else {
//				currentBookValue.setSaldo(saldo);
//			}
//		}
//		if (previousBookValue != null || currentBookValue != null) {
//			FiscalBalance fiscalBalance = new FiscalBalance();
//			if (previousBookValue != null) {
//				fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
//			}
//			if (currentBookValue != null) {
//				fiscalBalance.setEndSaldo(currentBookValue.getSaldo());
//			}
//			activaMap.put(INVOICES_TO_BE_PAID, fiscalBalance);
//		}
	}

//	private void handleStock() throws Exception {
//		BookValue currentBookValue = bookRepository.getBookValue(BalanceType.STOCK, bookYear);
//		BookValue previousBookValue = bookRepository.getBookValue(BalanceType.STOCK, bookYear - 1);
//		if (fiscalOverview.getRepurchase() != null && fiscalOverview.getRepurchase().intValue() > 0) {
//			if (currentBookValue == null) {
//				if (previousBookValue == null) {
//					BookValue newBookValue = new BookValue(STOCK, bookYear, fiscalOverview.getRepurchase());
//					newBookValue.setUser(UserCredentialManager.getUser());
//					bookRepository.persistEntity(newBookValue);
//				} else {
//					BookValue newBookValue = new BookValue(STOCK, bookYear, fiscalOverview.getRepurchase());
//					newBookValue.setSaldo(previousBookValue.getSaldo().add(fiscalOverview.getRepurchase()));
//					bookRepository.persistEntity(newBookValue);
//				}
//			}
//		}
//		if (previousBookValue != null || currentBookValue != null) {
//			FiscalBalance fiscalBalance = new FiscalBalance();
//			if (previousBookValue != null) {
//				fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
//			}
//			if (currentBookValue != null) {
//				fiscalBalance.setEndSaldo(currentBookValue.getSaldo());
//			}
//			activaMap.put(STOCK, fiscalBalance);
//		}
//	}

//	private void handleBusinessCar() throws Exception {
//		BigInteger carDepreciation = fiscalOverview.getAfschrijvingAuto();
//		BookValue currentBookValue = bookRepository.getBookValue(CAR, bookYear);
//		BookValue previousBookValue = bookRepository.getBookValue(CAR, bookYear - 1);
//
//		List<Activum> allActiva = activumDao.getActiveActiva(CAR);
//		BigDecimal totalPurchaseCostForAllActiva = BigDecimal.ZERO;
//		BigInteger totalRemainingValue = ZERO;
//		for (Activum activum2 : allActiva) {
//			totalPurchaseCostForAllActiva = totalPurchaseCostForAllActiva.add(activum2.getCost().getAmount());
//			totalRemainingValue = totalRemainingValue.add(activum2.getRemainingValue());
//		}
//
//        BigInteger newSaldo = ZERO;
//		if (previousBookValue != null) {
//			BigInteger carBookValue = previousBookValue.getSaldo();
//			if (currentBookValue == null) {
//				BookValue newValue = new BookValue();
//				newValue.setBalanceType(CAR);
//				newValue.setUser(UserCredentialManager.getUser());
//				newValue.setYear(bookYear);
//                newSaldo = carBookValue.subtract(carDepreciation);
//				newValue.setSaldo(newSaldo);
//				bookRepository.persistEntity(newValue);
//			} else {
//                newSaldo = carBookValue.subtract(carDepreciation);
//                currentBookValue.setSaldo(newSaldo);
//			}
//		}
//		if (previousBookValue != null || currentBookValue != null) {
//			FiscalBalance fiscalBalance = new FiscalBalance();
//			fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
//			fiscalBalance.setEndSaldo(newSaldo);
//			fiscalBalance.setTotalPurchaseCost(totalPurchaseCostForAllActiva);
//			fiscalBalance.setTotalRemainingValue(totalRemainingValue);
//			activaMap.put(CAR, fiscalBalance);
//		}
//	}

	private void handleMachinery(String username) throws Exception {
		BalanceType balanceType = MACHINERY;

//		BookValue previousBookValue = bookRepository.findBookValueByUserAndBalanceTypeAndBookYear(username, balanceType, bookYear - 1);
////		BookValue currentBookValue = bookRepository.getBookValue(balanceType, bookYear);
//
//		Activum activum = createActivum(balanceType);
//		activum.setEndDate(costCache.getBeginDate());
//		List<Activum> newActiva = activumDao.getNewActiva(balanceType);
//		BigDecimal totalCost = BigDecimal.ZERO;
//		for (Activum activum2 : newActiva) {
//			totalCost = totalCost.add(activum2.getCost().getAmount());
//		}
//		BigInteger totalCostForNewActiva = AmountHelper.roundToInteger(totalCost);
//
//		List<Activum> allActiva = activumDao.getActiveActiva(balanceType);
//		BigDecimal totalPurchaseCostForAllActiva = BigDecimal.ZERO;
//		BigInteger totalRemainingValue = ZERO;
//		for (Activum activum2 : allActiva) {
//			totalPurchaseCostForAllActiva = totalPurchaseCostForAllActiva.add(activum2.getCost().getAmount());
//			totalRemainingValue = totalRemainingValue.add(activum2.getRemainingValue());
//		}
//        BigInteger newSaldo;
//		if (previousBookValue != null) {
//			newSaldo = previousBookValue.getSaldo().add(totalCostForNewActiva).subtract(fiscalOverview.getAfschrijvingOverig());
//			if (totalRemainingValue.compareTo(newSaldo) == 1) {
//                newSaldo = totalRemainingValue;
//            }
//		} else {
//            newSaldo = totalCostForNewActiva;
//		}
//        insertOrUpdateBookValue(balanceType, currentBookValue, newSaldo);
//		if (previousBookValue != null || currentBookValue != null) {
//			FiscalBalance fiscalBalance = new FiscalBalance();
//            if (previousBookValue == null) {
//                fiscalBalance.setBeginSaldo(ZERO);
//            } else {
//                fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
//            }
//			fiscalBalance.setEndSaldo(newSaldo);
//			fiscalBalance.setTotalPurchaseCost(totalPurchaseCostForAllActiva);
//			fiscalBalance.setTotalRemainingValue(totalRemainingValue);
//			activaMap.put(balanceType, fiscalBalance);
//		}
	}
//
//	private void insertOrUpdateBookValue(BalanceType balanceType, BookValue currentBookValue, BigInteger newSaldo) {
//		if (newSaldo.compareTo(ZERO) != 0) {
//			if (currentBookValue == null) {
//				BookValue bookValue = createBookValue(balanceType);
//				bookValue.setSaldo(newSaldo);
//				bookValue.setUser(UserCredentialManager.getUser());
//				bookRepository.persistEntity(bookValue);
//			} else {
//				currentBookValue.setSaldo(newSaldo);
//				bookRepository.merge(currentBookValue);
//			}
//		}
//	}
//
//	private Activum createActivum(BalanceType balanceType) {
//		Activum activum = new Activum();
//		activum.setUser(UserCredentialManager.getUser());
//		activum.setBalanceType(balanceType);
//		return activum;
//	}
//
//	private BookValue createBookValue(BalanceType balanceType) {
//		BookValue bookValue = new BookValue();
//		bookValue.setYear(bookYear);
//		bookValue.setBalanceType(balanceType);
//		bookValue.setUser(UserCredentialManager.getUser());
//		return bookValue;
//	}
//
//	private void handleSettlement(FiscalOverview fiscalOverview) throws Exception {
//        BalanceType balanceType = OFFICE;
//		BookValue previousBookValue = bookRepository.getBookValue(OFFICE, bookYear - 1);
//		BookValue currentBookValue = bookRepository.getBookValue(OFFICE, bookYear);
//
//		List<Activum> allActiva = activumDao.getActiveActiva(OFFICE);
//		BigDecimal totalCostForActivum = BigDecimal.ZERO;
//        BigInteger totalDepreciation = ZERO;
//		for (Activum activum2 : allActiva) {
//			totalCostForActivum = totalCostForActivum.add(activum2.getCost().getAmount());
//            totalDepreciation = totalDepreciation.add(activum2.getDepreciation());
//		}
//        fiscalOverview.setSettlementDepreciation(totalDepreciation);
//		BigInteger totalCost = AmountHelper.roundToInteger(totalCostForActivum);
//
//        BigInteger newSaldo = ZERO;
//		if (previousBookValue != null) {
//			newSaldo = previousBookValue.getSaldo().subtract(totalDepreciation);
//		} else {
//			if (totalCost.compareTo(ZERO) == 1) {
//               newSaldo = totalCost;
//			}
//		}
//        insertOrUpdateBookValue(balanceType, currentBookValue, newSaldo);
//		if (previousBookValue != null || currentBookValue != null) {
//			FiscalBalance fiscalBalance = new FiscalBalance();
//			fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
//			fiscalBalance.setEndSaldo(newSaldo);
//			activaMap.put(OFFICE, fiscalBalance);
//		}
//
//	}
//
//	private void handleCurrentAssets(List<Cost> costs) throws Exception {
//		Liquidity liquidity = balanceCalculator.calculateAccountBalance(costs);
//		BookValue previousBookValue = bookRepository.getBookValue(CURRENT_ASSETS, bookYear - 1);
//		BookValue currentBookValue = bookRepository.getBookValue(CURRENT_ASSETS, bookYear);
//
//        BigInteger newSaldo = ZERO;
//        if (previousBookValue != null) {
//            newSaldo = previousBookValue.getSaldo();
//        }
//        newSaldo = newSaldo.add(liquidity.getRekeningBalans().toBigInteger());
//        newSaldo = newSaldo.add(liquidity.getSpaarBalans().toBigInteger());
//        insertOrUpdateBookValue(CURRENT_ASSETS, currentBookValue, newSaldo);
//		if (previousBookValue != null || currentBookValue != null) {
//			FiscalBalance fiscalBalance = new FiscalBalance();
//			if (previousBookValue != null) {
//				fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
//			}
//			fiscalBalance.setEndSaldo(newSaldo);
//			activaMap.put(CURRENT_ASSETS, fiscalBalance);
//		}
//	}

}