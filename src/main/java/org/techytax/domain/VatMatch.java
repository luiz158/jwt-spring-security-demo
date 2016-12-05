package org.techytax.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "VatMatch")
@Table(name = "BTWMATCH")
@Getter
@Setter
public class VatMatch extends VatMatchParent {

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "kostmatch_id")
	private Kostmatch publicCostMatch;


}
