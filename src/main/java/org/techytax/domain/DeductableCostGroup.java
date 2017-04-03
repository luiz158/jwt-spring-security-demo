package org.techytax.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeductableCostGroup implements Comparable<DeductableCostGroup> {
	private BigDecimal aftrekbaarBedrag;

	private CostType kostenSoort;
	
	public int compareTo(DeductableCostGroup o) {
		return (int) (this.kostenSoort.getId() - o.kostenSoort.getId());
	}
	
	@Override
	public String toString() {
		return "type: "+kostenSoort.getId()+", amount: "+aftrekbaarBedrag;
	}

}
