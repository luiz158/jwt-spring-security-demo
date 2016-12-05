package org.techytax.domain;

import lombok.Data;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedBigIntegerType;
import org.jasypt.hibernate4.type.EncryptedStringType;

import javax.persistence.*;

@MappedSuperclass
@Data
public class CostMatchParent {

	@Id
	@GeneratedValue
	protected Long id = 0L;
	
	@ManyToOne
	@JoinColumn(name="kostensoort_id")
	protected CostType costType;

	@OneToOne(mappedBy = "publicCostMatch", cascade = CascadeType.ALL)
	private VatMatch vatMatch;

}
