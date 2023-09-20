package com.loan.store.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.loan.store.entity.CustomerDetails;
import com.loan.store.entity.LenderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanLenderAggregateDetailResponseDto {
    private Long lenderId;
    private String lenderName;
    private Double aggregateRemainingAmount;
    private BigDecimal aggregateInterestPerDayInPercent;
    private BigDecimal aggregatePenaltyPerDayInPercent;

    public LoanLenderAggregateDetailResponseDto(Double aggregateRemainingAmount, BigDecimal aggregateInterestPerDayInPercent, BigDecimal aggregatePenaltyPerDayInPercent, LenderDetails lenderDetails) {
        this.aggregateRemainingAmount = aggregateRemainingAmount;
        this.aggregateInterestPerDayInPercent = aggregateInterestPerDayInPercent;
        this.aggregatePenaltyPerDayInPercent = aggregatePenaltyPerDayInPercent;
        this.lenderId = lenderDetails.getId();
        this.lenderName = lenderDetails.getFullName();
    }


}
