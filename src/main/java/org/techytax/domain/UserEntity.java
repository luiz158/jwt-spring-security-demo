package org.techytax.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tt_user")
@NamedQuery(name="UserEntity.findByName", query="SELECT user FROM UserEntity user WHERE user.username = ?") 
public class UserEntity extends TechyTaxUser {

	private static final long serialVersionUID = 1L;
	
	public UserEntity() {
		// Default
	}
	
	public UserEntity(TechyTaxUser user) {
		setUsername(user.getUsername());
		setId(user.getId());
	}

}
