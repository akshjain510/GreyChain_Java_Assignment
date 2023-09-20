package com.loan.store.controller;

import com.loan.store.dto.ResponseWrapper;
import com.loan.store.dto.response.LoanCustomerAggregateDetailResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("classpath:mockSql/loan_details.sql")
public class LoanDetailsControllerUnitTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test to find all loans")
    void testGetAllLoans() {
        ResponseWrapper responseWrapper = restTemplate.getForObject("/loans", ResponseWrapper.class);
        assertEquals(200, responseWrapper.getCode());
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("loans"));
    }

    @Test
    @DisplayName("Test to find loan by loan id")
    void testGetLoanByLoanId() {
        ResponseWrapper responseWrapper = restTemplate.getForObject("/loans/1", ResponseWrapper.class);
        assertEquals(200, responseWrapper.getCode());
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("alert"));
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("loan"));
    }

    @Test
    @DisplayName("Test to get all loans for a customer")
    void testGetLoanByCustomerId(){
        ResponseWrapper responseWrapper = restTemplate.getForObject("/loans/customer/1", ResponseWrapper.class);
        assertEquals(200, responseWrapper.getCode());
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("loan"));
        ArrayList<LoanCustomerAggregateDetailResponseDto> loanList = (ArrayList<LoanCustomerAggregateDetailResponseDto>) responseWrapper.getData().get("loan");
        assertEquals(3, loanList.size());
    }

    @Test
    @DisplayName("Test to get all loans given by lender")
    void testGetLoanByLenderId(){
        ResponseWrapper responseWrapper = restTemplate.getForObject("/loans/lender/2", ResponseWrapper.class);
        assertEquals(200, responseWrapper.getCode());
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("loan"));
        ArrayList<LoanCustomerAggregateDetailResponseDto> loanList = (ArrayList<LoanCustomerAggregateDetailResponseDto>) responseWrapper.getData().get("loan");
        assertEquals(2, loanList.size());
    }

    @Test
    @DisplayName("Test to get aggregate loan details group by customers")
    void testGetAggregateLoansOfCustomers(){
        ResponseWrapper responseWrapper = restTemplate.getForObject("/loans/aggregate/customer", ResponseWrapper.class);
        assertEquals(200, responseWrapper.getCode());
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("loans"));
    }

    @Test
    @DisplayName("Test to get aggregate loan details group by lenders")
    void testGetAggregateLoansByLenders(){
        ResponseWrapper responseWrapper = restTemplate.getForObject("/loans/aggregate/lender", ResponseWrapper.class);
        assertEquals(200, responseWrapper.getCode());
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("loans"));
    }

    @Test
    @DisplayName("Test to get aggregate loan details group by interest")
    void testGetAggregateLoansByInterest(){
        ResponseWrapper responseWrapper = restTemplate.getForObject("/loans/aggregate/interest", ResponseWrapper.class);
        assertEquals(200, responseWrapper.getCode());
        assertEquals(Boolean.TRUE, responseWrapper.getData().containsKey("loans"));
    }

}

