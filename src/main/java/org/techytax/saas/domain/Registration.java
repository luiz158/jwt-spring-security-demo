package org.techytax.saas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Locale;

@Entity
@Getter
@Setter
public class Registration {

  enum DeclationPeriod {
    QUARTERLY,
    YEARLY
  }

  @Entity
  class PersonalData {
    @Id
    @GeneratedValue
    Long id = 0L;
    String initials;
    String prefix;
    String surname;
    String email;
  }

  @Entity
  class CompanyData {
    @Id
    @GeneratedValue
    Long id = 0L;
    String address;
    String zipCode;
    String city;
    Long chamberOfCommerceNumber;
  }

  @Entity
  class FiscalData {
    @Id
    @GeneratedValue
    Long id = 0L;
    Long vatNumber;
    DeclationPeriod declarationPeriod;
  }

  @Id
  @GeneratedValue
  protected Long id = 0L;

  private String user;

  Locale registrationDate;

  @OneToOne
  PersonalData personalData;

  @OneToOne
  CompanyData companyData;

  @OneToOne
  FiscalData fiscalData;
}
