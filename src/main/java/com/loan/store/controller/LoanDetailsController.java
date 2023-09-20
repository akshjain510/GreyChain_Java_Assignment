package com.loan.store.controller;

import com.loan.store.dto.ResponseWrapper;
import com.loan.store.dto.request.LoanDetailRequestDto;
import com.loan.store.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanDetailsController {

    private final LoanService loanService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseWrapper allLoans(){
        return ResponseWrapper.getSuccessResponse(Map.of("loans", loanService.getDetailsOfAllLoans()), "Loan List");
    }

    @RequestMapping(value = "/add-update", method = RequestMethod.POST)
    public ResponseWrapper addUpdateLoanRequest(@RequestBody LoanDetailRequestDto loanDetailsDto){
        loanService.addUpdateLoanInfo(loanDetailsDto);
        if(loanDetailsDto.getId()==null)
            return ResponseWrapper.getSuccessResponse(null, "Loan Added Successfully");
        return ResponseWrapper.getSuccessResponse(null, "Loan Updated Successfully");
    }

    @RequestMapping(value = "/{loanId}", method = RequestMethod.GET)
    public ResponseWrapper getLoanByLoanId(@PathVariable Long loanId){
        return ResponseWrapper.getSuccessResponse(loanService.getLoanByLoanId(loanId), "Loan Details");
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseWrapper getLoanByCustomerId(@PathVariable Long customerId){
        return ResponseWrapper.getSuccessResponse(Map.of("loan", loanService.getLoanByCustomerId(customerId)), "Loan Details");
    }

    @RequestMapping(value = "/lender/{lenderId}", method = RequestMethod.GET)
    public ResponseWrapper getLoanByLenderId(@PathVariable Long lenderId){
        return ResponseWrapper.getSuccessResponse(Map.of("loan", loanService.getLoanByLenderId(lenderId)), "Loan Details");
    }

    @RequestMapping(value = "/aggregate/customer", method = RequestMethod.GET)
    public ResponseWrapper getAggregateLoansOfCustomers(){
        return ResponseWrapper.getSuccessResponse(Map.of("loans", loanService.getListOfAggregateLoanDetailsForCustomer()), "Aggregate Loan Details");
    }

    @RequestMapping(value = "/aggregate/lender", method = RequestMethod.GET)
    public ResponseWrapper getAggregateLoansByLenders(){
        return ResponseWrapper.getSuccessResponse(Map.of("loans", loanService.getListOfAggregateLoanDetailsForLenders()), "Aggregate Loan Details");
    }

    @RequestMapping(value = "/aggregate/interest", method = RequestMethod.GET)
    public ResponseWrapper getAggregateLoansByInterest(){
        return ResponseWrapper.getSuccessResponse(Map.of("loans", loanService.getListOfAggregateLoanDetailsByInterest()), "Aggregate Loan Details");
    }


}
