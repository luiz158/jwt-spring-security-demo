package org.techytax.saas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
  @Getter
  @Setter
  public static class PersonalData {
    @Id
    @GeneratedValue
    Long id = 0L;
    String initials;
    String prefix;
    String surname;
    String email;
  }

  @Entity
  @Getter
  @Setter
  static class CompanyData {
    @Id
    @GeneratedValue
    Long id = 0L;
    String address;
    String zipCode;
    String city;
    Long chamberOfCommerceNumber;
  }

  @Entity
  @Getter
  @Setter
  static class FiscalData {
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
  private String password;

  Locale registrationDate;

  @OneToOne(cascade = {CascadeType.ALL})
  PersonalData personalData;

  @OneToOne(cascade = {CascadeType.ALL})
  CompanyData companyData;

  @OneToOne(cascade = {CascadeType.ALL})
  FiscalData fiscalData;
}
