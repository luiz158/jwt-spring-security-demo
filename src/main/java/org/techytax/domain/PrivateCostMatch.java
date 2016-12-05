package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = PrivateCostMatch.FOR_TYPE, query = "SELECT cm FROM PrivateCostMatch cm WHERE cm.user = :user AND cm.costType = :costType")
})
@Table(name = "kostmatch_private")
@Getter
@Setter
public class PrivateCostMatch extends CostMatchParent {
	
	public static final String FOR_TYPE = "PrivateCostMatch.FOR_TYPE";

	@ManyToOne
	@JoinColumn(name = "user_id", updatable=false)
	private UserEntity user;
	
	public void setUser(TechyTaxUser user) {
		this.user = new UserEntity(user);
	}

	@OneToOne(mappedBy = "privateCostMatch", cascade = CascadeType.ALL, orphanRemoval = true)
	private VatMatchPrivate vatMatchPrivate;

	@OneToOne(mappedBy = "privateCostMatch", cascade = CascadeType.ALL, orphanRemoval = true)
	private SplitMatch splitMatch;
	
	@Column(name = "match_text")
	@Type(type = "encryptedString")
	protected String matchText;
	
}
