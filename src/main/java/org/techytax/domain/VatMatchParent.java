package org.techytax.domain;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class VatMatchParent {
	
	@Id
    @GeneratedValue
    private Long id;

	@Column(name = "btw_type")
	@Enumerated(EnumType.ORDINAL)	
	private VatType vatType;

}
