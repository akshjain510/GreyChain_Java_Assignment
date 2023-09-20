package com.loan.store.entity;

import com.loan.store.common.enums.IncomeType;
import com.loan.store.entity.base.BaseEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
public class CustomerDetails extends BaseEntity {
    private String fullName;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;
}
