package org.techytax.domain.fiscal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.repository.ActivumRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import static java.math.BigInteger.ZERO;
import static org.techytax.domain.BalanceType.CAR;
import static org.techytax.domain.BalanceType.MACHINERY;
import static org.techytax.domain.BalanceType.OFFICE;
import static org.techytax.helper.AmountHelper.roundToInteger;

@Component
@Data
public class Depreciation {
  private BigInteger afschrijvingAuto;
  private BigInteger machineryDepreciation;
  private BigInteger settlementDepreciation;

  @Autowired
  @JsonIgnore
  private ActivumRepository activumRepository;

  private Collection<Activum> machineryList;
  private Collection<Activum> carList;
  private Collection<Activum> officeList;

  public BigInteger getAfschrijvingTotaal() {
    return afschrijvingAuto.add(settlementDepreciation).add(machineryDepreciation);
  }

  public void handleDepreciations(String username) throws Exception {
    afschrijvingAuto = ZERO;
    machineryDepreciation = ZERO;
    settlementDepreciation = ZERO;
//    machineryList = activumRepository.findActivumsByUserAndBalanceType(username, MACHINERY);
//    carList = activumRepository.findActivumsByUserAndBalanceType(username, CAR);
//    officeList = activumRepository.findActivumsByUserAndBalanceType(username, OFFICE);

    for (Activum activum: machineryList) {
      machineryDepreciation = machineryDepreciation.add(activum.getDepreciation());
    }
    for (Activum activum: carList) {
      afschrijvingAuto = afschrijvingAuto.add(activum.getDepreciation());
    }
    for (Activum activum: officeList) {
      settlementDepreciation = settlementDepreciation.add(activum.getDepreciation());
    }
  }
}
