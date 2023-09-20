package com.loan.store.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.loan.store.entity.CustomerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanInterestAggregateDetailResponseDto {
    private Double aggregateRemainingAmount;
    private BigDecimal aggregateInterestPerDayInPercent;
    private BigDecimal aggregatePenaltyPerDayInPercent;

    public LoanInterestAggregateDetailResponseDto(Double aggregateRemainingAmount, BigDecimal aggregateInterestPerDayInPercent, BigDecimal aggregatePenaltyPerDayInPercent) {
        this.aggregateRemainingAmount = aggregateRemainingAmount;
        this.aggregateInterestPerDayInPercent = aggregateInterestPerDayInPercent;
        this.aggregatePenaltyPerDayInPercent = aggregatePenaltyPerDayInPercent;
    }


}
