package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue
	protected Long id = 0L;

	@NotNull
	private String user;

	private String name;

	private String description;

	private BigInteger commerceNr;

	private String address;

	private BigInteger number;

	private String numberExtension;

	private String postalCode;

	private String city;

	private String contact;

	private String emailInvoice;

	private String telephone;

	private String fax;

	private String website;

	public String getFullAddress() {
		StringBuffer fullAddress = new StringBuffer();
		fullAddress.append(address);
		if (number != null) {
			fullAddress.append(" ");
			fullAddress.append(number);
		}
		if (numberExtension != null) {
			fullAddress.append(" ");
			fullAddress.append(numberExtension);
		}
		fullAddress.append(", ");
		fullAddress.append(postalCode);
		fullAddress.append(", ");
		fullAddress.append(city);
		return fullAddress.toString();
	}
}
