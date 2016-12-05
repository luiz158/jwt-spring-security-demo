package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NamedQueries({
		@NamedQuery(name = BookValue.HISTORY, query = "SELECT bv FROM BookValue bv WHERE bv.user = :user order by bv.balanceType asc, bv.bookYear desc"),
		@NamedQuery(name = BookValue.FOR_YEAR, query = "SELECT bv FROM BookValue bv WHERE bv.user = :user AND bv.bookYear = :bookYear order by bv.balanceType asc"),
		@NamedQuery(name = BookValue.GET, query = "SELECT bv FROM BookValue bv WHERE bv.bookYear = :bookYear and bv.user = :user and bv.balanceType = :balanceType"),
		@NamedQuery(name = BookValue.FOR_YEAR_AND_TYPES, query = "SELECT bv FROM BookValue bv WHERE bv.user = :user AND bv.bookYear = :bookYear AND bv.balanceType IN :balanceTypes ORDER BY bv.balanceType asc") })
@Table(name = "bookvalue")
@Getter
@Setter
public class BookValue {

	public static final String HISTORY = "org.techytax.domain.BookValue.HISTORY";
	public static final String FOR_YEAR = "org.techytax.domain.BookValue.FOR_YEAR";
	public static final String GET = "org.techytax.domain.BookValue.GET";
	public static final String FOR_YEAR_AND_TYPES = "org.techytax.domain.BookValue.FOR_YEAR_AND_TYPES";

	@Id
	@GeneratedValue
	protected Long id = 0L;

	private String user;

	@Enumerated(EnumType.ORDINAL)
	private BalanceType balanceType;

	private int bookYear;

	@Column(precision = 10)
	private BigInteger saldo;

	public BookValue() {
	}

	public BookValue(BalanceType balanceType, int bookYear, BigInteger saldo) {
		this.balanceType = balanceType;
		this.bookYear = bookYear;
		this.saldo = saldo;
	}

	public String getDescription() {
		return balanceType.getKey();
	}

    @Override
    public String toString() {
        return balanceType + "," + bookYear + "," + saldo;
    }
}
