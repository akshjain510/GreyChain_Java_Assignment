package com.loan.store.entity;

import com.loan.store.entity.base.BaseEntity;
import lombok.Data;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
public class LoanDetails extends BaseEntity {
    /**
    We could have used `ManyToOne` to define CustomerDetails and LenderDetails
    relationship with LoadDetails table but usually when project's business logic is complex or
    one want's more grain level control then we can define relationship in our own way.

    i.e.

    @ManyToOne
    @JoinColumn(name = "id")
    private CustomerDetails customerDetails;

    **/

    private Long customerId;
    private Long lenderId;
    private Double amount;
    private Double remainingAmount;
    private LocalDate paymentDate;
    private Float interestPerDayInPercent;
    private LocalDate dueDate;
    private Float penaltyPerDayInPercent;
    private Boolean cancel;
}
