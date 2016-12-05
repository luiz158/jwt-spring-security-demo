package org.techytax.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NamedQueries({
		@NamedQuery(name = Cost.FOR_PERIOD, query = "SELECT c FROM Cost c WHERE c.user = :user AND c.date >= :beginDate AND c.date <= :endDate order by c.date asc"),
		@NamedQuery(name = Cost.FOR_PERIOD_AND_TYPES, query = "SELECT c FROM Cost c WHERE c.date >= :beginDate AND c.date <= :endDate AND c.costType IN :costTypes AND c.user = :user"),
		@NamedQuery(name = Cost.FOR_PERIOD_AND_VAT_DECLARABLE, query = "SELECT c FROM Cost c WHERE c.date >= :beginDate AND c.date <= :endDate AND c.costType.btwVerrekenbaar = true AND c.user = :user"),
		@NamedQuery(name = Cost.FOR_PERIOD_AND_ACCOUNT, query = "SELECT c FROM Cost c WHERE c.date >= :beginDate AND c.date <= :endDate AND c.costType.balansMeetellen = true AND c.user = :user") })
@Table(name = "cost")
@Getter
@Setter
public class Cost {

	public static final String FOR_PERIOD = "Cost.FOR_PERIOD";
	public static final String FOR_PERIOD_AND_TYPES = "Cost.FOR_PERIOD_AND_TYPES";
	public static final String FOR_PERIOD_AND_VAT_DECLARABLE = "Cost.FOR_PERIOD_AND_VAT_DECLARABLE";
	public static final String FOR_PERIOD_AND_ACCOUNT = "Cost.FOR_PERIOD_AND_ACCOUNT";

	@Id
	@GeneratedValue
	protected Long id = 0L;

	private String user;

	@Column(precision = 10, scale = 2)
	private BigDecimal amount;

	@Column(precision = 10, scale = 2)
	private BigDecimal vat = BigDecimal.ZERO;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private LocalDate date;

	@ManyToOne
	private CostType costType;

	private String description;

	public long getCostTypeId() {
		return costType.getId();
	}

	public boolean isIncoming() {
		return costType.isBijschrijving();
	}

	public boolean isInvestment() { return costType != null ? costType.isInvestering(): false; }

	public void roundValues() {
		if (amount != null) {
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		if (vat != null) {
			vat = vat.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
}
