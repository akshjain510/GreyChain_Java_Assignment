package com.loan.store.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.loan.store.entity.CustomerDetails;
import com.loan.store.entity.LenderDetails;
import com.loan.store.entity.LoanDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanCustomerAggregateDetailResponseDto {
    private Long customerId;
    private String customerName;
    private Double aggregateRemainingAmount;
    private BigDecimal aggregateInterestPerDayInPercent;
    private BigDecimal aggregatePenaltyPerDayInPercent;

    public LoanCustomerAggregateDetailResponseDto(Double aggregateRemainingAmount, BigDecimal aggregateInterestPerDayInPercent, BigDecimal aggregatePenaltyPerDayInPercent, CustomerDetails customerDetails) {
        this.aggregateRemainingAmount = aggregateRemainingAmount;
        this.aggregateInterestPerDayInPercent = aggregateInterestPerDayInPercent;
        this.aggregatePenaltyPerDayInPercent = aggregatePenaltyPerDayInPercent;
        this.customerId = customerDetails.getId();
        this.customerName = customerDetails.getFullName();
    }


}
