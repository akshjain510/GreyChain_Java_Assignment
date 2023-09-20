package com.loan.store.service;

import com.loan.store.common.exception.DataValidationException;
import com.loan.store.common.utils.ParserUtils;
import com.loan.store.dto.request.LoanDetailRequestDto;
import com.loan.store.dto.response.LoanCustomerAggregateDetailResponseDto;
import com.loan.store.dto.response.LoanDetailResponseDto;
import com.loan.store.dto.response.LoanInterestAggregateDetailResponseDto;
import com.loan.store.dto.response.LoanLenderAggregateDetailResponseDto;
import com.loan.store.entity.LoanDetails;
import com.loan.store.repository.LoanDetailsRepository;
import com.loan.store.service.helper.LoanHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanHelper loanHelper;
    private final LoanDetailsRepository loanDetailsRepository;
    private final ParserUtils parserUtils;



    public void addUpdateLoanInfo(LoanDetailRequestDto dto){
        loanHelper.validateLoanRequest(dto);
        LoanDetails loanDetails;
        if(dto.getId()!=null){
            loanDetails = loanDetailsRepository.findById(dto.getId()).orElseThrow(()-> new DataValidationException("No Loan found with this ID"));
            loanDetails = loanHelper.updateLoanDetails(loanDetails, dto);
        }
        else {
            dto.setRemainingAmount(dto.getAmount());
            loanDetails = parserUtils.extractObject(dto, LoanDetails.class);
        }
        loanDetailsRepository.save(loanDetails);
    }

    public List<LoanDetailResponseDto> getDetailsOfAllLoans(){
        return loanDetailsRepository.getAllLoanDetails();
    }

    public HashMap<String, Object> getLoanByLoanId(Long loanId){
        LoanDetailResponseDto responseDto = loanDetailsRepository.getLoanByLoanId(loanId);
        if(responseDto==null){
            return null;
        }
        HashMap<String, Object> loan = new HashMap<>();
        if(responseDto.getDueDate().isBefore(LocalDate.now())) {
            loan.put("alert", "Loan has crossed due date");
        }
        loan.put("loan", responseDto);
        return loan;
    }

    public List<LoanDetailResponseDto> getLoanByCustomerId(Long customerId){
        return loanDetailsRepository.getAllLoansByCustomerId(customerId);
    }

    public List<LoanDetailResponseDto> getLoanByLenderId(Long lenderId){
        return loanDetailsRepository.getAllLoansByLenderId(lenderId);
    }

    public List<LoanCustomerAggregateDetailResponseDto> getListOfAggregateLoanDetailsForCustomer(){
        return loanDetailsRepository.getAggregateLoanDetailsForCustomers();
    }

    public List<LoanLenderAggregateDetailResponseDto> getListOfAggregateLoanDetailsForLenders(){
        return loanDetailsRepository.getAggregateLoanDetailsForLenders();
    }

    public List<LoanInterestAggregateDetailResponseDto> getListOfAggregateLoanDetailsByInterest(){
        return loanDetailsRepository.getAggregateLoanDetailsByInterest();
    }
}
