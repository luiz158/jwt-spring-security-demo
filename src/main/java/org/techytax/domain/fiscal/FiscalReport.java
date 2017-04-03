package org.techytax.domain.fiscal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
public class FiscalReport {

    @Id
    @GeneratedValue
    protected Long id = 0L;

    @NotNull
    private String user;

    @NotNull
    private Integer year;

    private FiscalOverview fiscalOverview;
}
