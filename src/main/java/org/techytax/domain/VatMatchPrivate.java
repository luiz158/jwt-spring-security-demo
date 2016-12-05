package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "VatMatchPrivate")
@Table(name = "btwmatch_private")
@Getter
@Setter
public class VatMatchPrivate extends VatMatchParent {
	
	@OneToOne
	@JoinColumn(name = "kostmatch_id" )
	private PrivateCostMatch privateCostMatch;

}
