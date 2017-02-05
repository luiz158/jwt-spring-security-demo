package org.techytax.invoice;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDto {

	private float unitsOfWork;
	private String consumerAddress;
	private String consumerName;
	private int vat;
	private String vatIdNr;
	private String chamberOfCommerceNumber;
	private String email;
	private String emailBcc;
	private String emailCc;
	private int invoiceNumber;
	private String invoiceDate;
	private int nofDays;
	private String expiryDate;
	
	private int year;
	private String month;
	private BigDecimal netAmount;

	private String activityDescription;
	private BigDecimal rate;
	private BigDecimal totalAmount;
	private BigDecimal vatAmount;
	private int discountPercentage;
	private BigDecimal discount;
	private BigDecimal netAmountAfterDiscount;

}
