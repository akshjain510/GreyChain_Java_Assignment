package com.loan.store.repository;

import com.loan.store.common.exception.DataValidationException;
import com.loan.store.dto.response.LoanCustomerAggregateDetailResponseDto;
import com.loan.store.dto.response.LoanDetailResponseDto;
import com.loan.store.dto.response.LoanInterestAggregateDetailResponseDto;
import com.loan.store.dto.response.LoanLenderAggregateDetailResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("classpath:mockSql/loan_details.sql")
public class LoanDetailsRepositoryUnitTest {

    @Autowired
    private LoanDetailsRepository loanDetailsRepository;

    @Test
    @DisplayName("Test to find all loans")
    void testFindAllLoans(){
        List<LoanDetailResponseDto> loanDetailsList = this.loanDetailsRepository.getAllLoanDetails();
        assertEquals(6, loanDetailsList.size());
        assertEquals("Customer 1", loanDetailsList.get(0).getCustomerName());
        assertEquals("Lender 1", loanDetailsList.get(0).getLenderName());
    }

    @Test
    @DisplayName("Test to find loan by loan id")
    void testGetLoanByLoanId(){
        LoanDetailResponseDto loanDetails1 = this.loanDetailsRepository.getLoanByLoanId(3L);
        LoanDetailResponseDto loanDetails2 = this.loanDetailsRepository.getLoanByLoanId(200L);
        assertNull(loanDetails2);
        assertEquals(3, loanDetails1.getCustomerId());
    }

    @Test
    @DisplayName("Test when loan is not present")
    void testExceptionThrownWhenIdNotFound(){
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            this.loanDetailsRepository.findById(200L).orElseThrow(()-> new DataValidationException("No Loan found with this ID"));
        });
        assertEquals("No Loan found with this ID", exception.getMessage());
    }

    @Test
    @DisplayName("Test to get all loans for a customer")
    void testGetAllLoansByCustomerId(){
        List<LoanDetailResponseDto> customerId2 = this.loanDetailsRepository.getAllLoansByCustomerId(2L);
        List<LoanDetailResponseDto> customerId200 = this.loanDetailsRepository.getAllLoansByCustomerId(200L);
        assertEquals(0, customerId200.size());
        assertEquals(2, customerId2.size());
    }

    @Test
    @DisplayName("Test to get all loans given by lender")
    void testGetAllLoansByLenderId(){
        List<LoanDetailResponseDto> lenderId2 = this.loanDetailsRepository.getAllLoansByLenderId(2L);
        List<LoanDetailResponseDto> lenderId200 = this.loanDetailsRepository.getAllLoansByLenderId(200L);
        assertEquals(0, lenderId200.size());
        assertEquals(2, lenderId2.size());
    }

    @Test
    @DisplayName("Test to get aggregate loan details group by customers")
    void testGetAggregateLoanDetailsForCustomers(){
        List<LoanCustomerAggregateDetailResponseDto> list = this.loanDetailsRepository.getAggregateLoanDetailsForCustomers();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).getCustomerId());
        assertEquals(new BigDecimal(2.63).setScale(2, RoundingMode.HALF_UP), list.get(0).getAggregateInterestPerDayInPercent());
    }

    @Test
    @DisplayName("Test to get aggregate loan details group by lenders")
    void testGetAggregateLoanDetailsForLenders(){
        List<LoanLenderAggregateDetailResponseDto> list = this.loanDetailsRepository.getAggregateLoanDetailsForLenders();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).getLenderId());
        assertEquals(new BigDecimal(0.53).setScale(2, RoundingMode.HALF_UP), list.get(0).getAggregatePenaltyPerDayInPercent());
    }

    @Test
    @DisplayName("Test to get aggregate loan details group by interest")
    void testGetAggregateLoanDetailsByInterest(){
        List<LoanInterestAggregateDetailResponseDto> list = this.loanDetailsRepository.getAggregateLoanDetailsByInterest();
        assertEquals(6, list.size());
    }

}
