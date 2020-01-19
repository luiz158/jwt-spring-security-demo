package org.techytax.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class CostMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id = 0L;

    @NotNull
    private String user;

    @NotNull
    @Size(min = 2, max = 50)
    private String matchString;

    @ManyToOne
    private CostType costType;

    private int costCharacter;

    @Enumerated(EnumType.ORDINAL)
    private VatType vatType;

    private int percentage;
    private int fixedAmount;

}
