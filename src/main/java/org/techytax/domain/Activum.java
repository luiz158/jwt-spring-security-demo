package org.techytax.domain;

import lombok.Getter;
import lombok.Setter;
import org.techytax.helper.DepreciationHelper;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = Activum.NEW_ACTIVA, query = "SELECT act FROM Activum act WHERE act.balanceType = :balanceType AND ((act.cost.date >= :fiscalStartDate AND act.cost.date <= :fiscalEndDate) OR (act.startDate <= :fiscalStartDate AND act.startDate <= :fiscalEndDate)) AND act.endDate = null AND act.user = :user"),
        @NamedQuery(name = Activum.ALL_ACTIVA, query = "SELECT act FROM Activum act WHERE act.user = :user ORDER BY act.cost.date ASC"),
        @NamedQuery(name = Activum.GET_ACTIVUM_FOR_COST, query = "SELECT act FROM Activum act WHERE act.user = :user AND act.cost = :cost"),
        @NamedQuery(name = Activum.ACTIVE_ACTIVA, query = "SELECT act FROM Activum act WHERE act.user = :user AND act.endDate = null ORDER BY act.cost.date ASC"),
        @NamedQuery(name = Activum.ACTIVE_ACTIVA_FOR_TYPE, query = "SELECT act FROM Activum act WHERE act.balanceType = :balanceType AND act.user = :user AND act.endDate = null AND (act.startDate = null OR act.startDate <= :startDate) ORDER BY act.cost.date ASC")})
@Table(name = "activa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Activum {

    public static final String NEW_ACTIVA = "Activum.NEW_ACTIVA";
    public static final String ALL_ACTIVA = "Activum.ALL_ACTIVA";
    public static final String GET_ACTIVUM_FOR_COST = "Activum.GET_ACTIVUM_FOR_COST";
    public static final String ACTIVE_ACTIVA = "Activum.ACTIVE_ACTIVA";
    public static final String ACTIVE_ACTIVA_FOR_TYPE = "Activum.ACTIVE_ACTIVA_FOR_TYPE";

    @Id
    @GeneratedValue
    protected Long id = 0L;

    private String user;

    @Column(precision = 10, scale = 2)
    private BigInteger remainingValue;

    private LocalDate endDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Cost cost;

    @Enumerated(EnumType.ORDINAL)
    private BalanceType balanceType;

    private LocalDate startDate;

    private int nofYearsForDepreciation;

    public long getCostId() {
        return cost.getId();
    }

    public LocalDate getStartDate() {
        if (startDate != null) {
            return startDate;
        } else {
            return cost.getDate();
        }
    }

    public String getOmschrijving() {
        return balanceType.getKey();
    }

    public BigInteger getDepreciation() {
        DepreciationHelper depreciationHelper = new DepreciationHelper();
        return depreciationHelper.getDepreciation(this);
    }

}
