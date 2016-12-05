package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedBigIntegerType;
import org.jasypt.hibernate4.type.EncryptedStringType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@NamedQuery(name = Settlement.GET, query = "SELECT s FROM Settlement s WHERE s.user = :user")
@Table(name = "settlement")
@DiscriminatorValue("S")
@Getter
@Setter
public class Settlement extends Activum {
	
	public static final String GET = "Settlement.GET";

	private String description;
	
	private BigInteger purchasePrice;
	
	private BigDecimal startupCosts;
	
	private int nofSquareMetersBusiness;
	private int nofSquareMetersPrivate;
	
	private BigInteger wozValue;
	
	private BigInteger terrainValue;
	
//	@Type(type = "encryptedInteger")
//	private BigInteger eigenWoningForfaitBusiness;
//	
//	@Type(type = "encryptedInteger")
//	private BigInteger eigenWoningForfaitPrivate;
//	
//	@Type(type = "encryptedInteger")
//	private BigInteger fictiefRendement;

}
