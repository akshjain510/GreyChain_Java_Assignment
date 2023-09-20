package com.loan.store.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.loan.store.common.enums.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDetailRequestDto {
    private Long id;
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
