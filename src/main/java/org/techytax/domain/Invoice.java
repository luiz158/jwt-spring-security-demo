package org.techytax.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue
    protected Long id = 0L;

    @NotNull
    private String user;

    @NotNull
    @ManyToOne
    private Project project;

    @NotNull
    private String invoiceNumber;

    @NotNull
    private String month;

    private float unitsOfWork;
    private int discountPercentage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate sent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate paid;
}
