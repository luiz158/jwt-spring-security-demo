package org.techytax.domain;

public enum BalanceType {
	NONE("balance.type.none", null), MACHINERY("balance.type.machines", true), CAR("balance.type.car", true), CURRENT_ASSETS(
			"balance.type.liquid.assets", true), NON_CURRENT_ASSETS(
			"balance.type.equity", false), PENSION("balance.type.pension", false), STOCK(
			"balance.type.stock", true), OFFICE("balance.type.settlement", true), VAT_TO_BE_PAID(
			"balance.type.debt.sales.tax", false), INVOICES_TO_BE_PAID(
			"balance.type.loans.customers", true);

	private String key;
	
	private Boolean isActivum;

	private BalanceType(String key, Boolean isActivum) {
		this.key = key;
		this.isActivum = isActivum;
	}

	public String getKey() {
		return key;
	}
	
	public Boolean isActivum() {
		return isActivum;
	}

	public static BalanceType getInstance(int type) {
		BalanceType balanceType;
		switch (type) {
		case 1:
			balanceType = MACHINERY;
			break;
		case 2:
			balanceType = CAR;
			break;
		case 3:
			balanceType = CURRENT_ASSETS;
			break;
		case 4:
			balanceType = NON_CURRENT_ASSETS;
			break;
		case 5:
			balanceType = PENSION;
			break;
		case 6:
			balanceType = STOCK;
			break;
		case 7:
			balanceType = OFFICE;
			break;
		case 8:
			balanceType = VAT_TO_BE_PAID;
			break;
		case 9:
			balanceType = INVOICES_TO_BE_PAID;
			break;
		default:
			balanceType = null;
			break;
		}
		return balanceType;
	}
}
