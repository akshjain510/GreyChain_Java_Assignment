package com.loan.store.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.loan.store.entity.CustomerDetails;
import com.loan.store.entity.LenderDetails;
import com.loan.store.entity.LoanDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDetailResponseDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long lenderId;
    private String lenderName;
    private Double amount;
    private Double remainingAmount;
    private LocalDate paymentDate;
    private Float interestPerDayInPercent;
    private LocalDate dueDate;
    private Float penaltyPerDayInPercent;
    private Boolean cancel;

    public LoanDetailResponseDto(LoanDetails loanDetails, CustomerDetails customerDetails, LenderDetails lenderDetails){
        this.id = loanDetails.getId();
        this.customerId = customerDetails.getId();
        this.customerName = customerDetails.getFullName();
        this.lenderId = lenderDetails.getId();
        this.lenderName = lenderDetails.getFullName();
        this.amount = loanDetails.getAmount();
        this.remainingAmount = loanDetails.getRemainingAmount();
        this.paymentDate = loanDetails.getPaymentDate();
        this.interestPerDayInPercent = loanDetails.getInterestPerDayInPercent();
        this.dueDate = loanDetails.getDueDate();
        this.penaltyPerDayInPercent = loanDetails.getPenaltyPerDayInPercent();
        this.cancel = loanDetails.getCancel();
    }

}
