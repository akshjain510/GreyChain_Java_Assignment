package com.loan.store.service.helper;

import com.loan.store.common.exception.DataValidationException;
import com.loan.store.dto.request.LoanDetailRequestDto;
import com.loan.store.entity.LoanDetails;
import com.loan.store.repository.CustomerDetailsRepository;
import com.loan.store.repository.LenderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanHelper {

    private final CustomerDetailsRepository customerDetailsRepository;
    private final LenderDetailsRepository lenderDetailsRepository;

    /**
     * we are assuming that:
     * 1)payment date can be equal to due date
     * 2)user can add payment date and due date of past for old records
     * 3)remaining amount consist only of base amount not interest amount
     * 4)interest is calculated only on remaining amount not base amount
     **/
    public void validateLoanRequest(LoanDetailRequestDto dto){
        if(dto==null){
            throw new DataValidationException("Loan details not provided");
        }
        if(dto.getCustomerId()==null){
            throw new DataValidationException("Customer ID not provided");
        }
        if(dto.getLenderId()==null){
            throw new DataValidationException("Lender ID not provided");
        }
        if(dto.getAmount()==null || dto.getAmount()<1000){
            throw new DataValidationException("Loan amount must be at least 1000");
        }
        if(dto.getId()==null && dto.getRemainingAmount()!=null){
            throw new DataValidationException("Remaining Amount must be null for new loan request");
        }
        if(dto.getId()!=null && (dto.getRemainingAmount()==null || dto.getRemainingAmount()<0)){
            throw new DataValidationException("Remaining Amount must be a positive value for update loan request");
        }
        if(dto.getId()!=null && dto.getRemainingAmount()>dto.getAmount()){
            throw new DataValidationException("Remaining Amount can not be more then amount");
        }
        if(dto.getPaymentDate()==null){
            throw new DataValidationException("Payment date is required");
        }
        if(dto.getInterestPerDayInPercent()==null || dto.getInterestPerDayInPercent()<=0){
            throw new DataValidationException("Interest per day must be a positive value");
        }
        if(dto.getDueDate()==null){
            throw new DataValidationException("Due date is required");
        }
        if(dto.getPenaltyPerDayInPercent()==null || dto.getPenaltyPerDayInPercent()<=0){
            throw new DataValidationException("Penalty per day must be a positive value");
        }
        if(dto.getCancel()==null){
            dto.setCancel(Boolean.FALSE);
        }

        if(dto.getPaymentDate().isAfter(dto.getDueDate())){
            throw new DataValidationException("Payment Date can not be after Due Date");
        }

        customerDetailsRepository.findById(dto.getCustomerId()).orElseThrow(()-> new DataValidationException("No customer exists with this ID"));
        lenderDetailsRepository.findById(dto.getLenderId()).orElseThrow(()-> new DataValidationException("No lender exists with this ID"));

    }

    public LoanDetails updateLoanDetails(LoanDetails loanDetails, LoanDetailRequestDto dto){
        loanDetails.setCustomerId(dto.getCustomerId());
        loanDetails.setLenderId(dto.getLenderId());
        loanDetails.setAmount(dto.getAmount());
        loanDetails.setRemainingAmount(dto.getRemainingAmount());
        loanDetails.setPaymentDate(dto.getPaymentDate());
        loanDetails.setInterestPerDayInPercent(dto.getInterestPerDayInPercent());
        loanDetails.setDueDate(dto.getDueDate());
        loanDetails.setPenaltyPerDayInPercent(dto.getPenaltyPerDayInPercent());
        loanDetails.setCancel(dto.getCancel());
        return loanDetails;
    }
}
