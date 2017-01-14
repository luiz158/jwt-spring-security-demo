package org.techytax.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.techytax.helper.DepreciationHelper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = Activum.NEW_ACTIVA, query = "SELECT act FROM Activum act WHERE act.balanceType = :balanceType AND ((act.startDate >= :fiscalStartDate AND act.startDate <= :fiscalEndDate) OR (act.startDate <= :fiscalStartDate AND act.startDate <= :fiscalEndDate)) AND act.endDate = null AND act.user = :user"),
        @NamedQuery(name = Activum.ALL_ACTIVA, query = "SELECT act FROM Activum act WHERE act.user = :user ORDER BY act.startDate ASC"),
        @NamedQuery(name = Activum.ACTIVE_ACTIVA, query = "SELECT act FROM Activum act WHERE act.user = :user AND act.endDate = null ORDER BY act.startDate ASC"),
        @NamedQuery(name = Activum.ACTIVE_ACTIVA_FOR_TYPE, query = "SELECT act FROM Activum act WHERE act.balanceType = :balanceType AND act.user = :user AND act.endDate = null AND (act.startDate = null OR act.startDate <= :startDate) ORDER BY act.startDate ASC")})
@Table(name = "activa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Activum {

    static final String NEW_ACTIVA = "Activum.NEW_ACTIVA";
    static final String ALL_ACTIVA = "Activum.ALL_ACTIVA";
    static final String ACTIVE_ACTIVA = "Activum.ACTIVE_ACTIVA";
    static final String ACTIVE_ACTIVA_FOR_TYPE = "Activum.ACTIVE_ACTIVA_FOR_TYPE";

    @Id
    @GeneratedValue
    protected Long id = 0L;

    @NotNull
    private String user;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @Column(precision = 10, scale = 2)
    private BigInteger remainingValue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate purchaseDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate endDate;

    @Enumerated(EnumType.ORDINAL)
    private BalanceType balanceType;

    private int nofYearsForDepreciation;

    public String getOmschrijving() {
        return balanceType.getKey();
    }

    public BigInteger getDepreciation() {
        DepreciationHelper depreciationHelper = new DepreciationHelper();
        return depreciationHelper.getDepreciation(this);
    }

}
