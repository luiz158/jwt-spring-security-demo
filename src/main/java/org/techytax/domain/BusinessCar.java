package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@NamedQuery(name = BusinessCar.GET, query = "SELECT c FROM BusinessCar c WHERE c.user = :user")
@Table(name = "businesscar")
@DiscriminatorValue("C")
@Getter
@Setter
public class BusinessCar extends Activum {

	public static final String GET = "BusinessCar.GET";
	
	private BigInteger fiscalIncomeAddition;
	
	private BigInteger vatCorrectionForPrivateUsage;
	
}
