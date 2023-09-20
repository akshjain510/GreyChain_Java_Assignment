package com.loan.store.service;


import com.loan.store.dto.request.LoanDetailRequestDto;
import com.loan.store.repository.LoanDetailsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
@Sql("classpath:mockSql/loan_details.sql")
public class LoanServiceUnitTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanDetailsRepository loanDetailsRepository;

    @Test
    @DisplayName("Test add loan details")
    void testAddLoadDetails() {
        LoanDetailRequestDto dto = createDto(1L,1L,10000D,null, LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        assertDoesNotThrow(()->loanService.addUpdateLoanInfo(dto));
        assertEquals(7,loanDetailsRepository.getAllLoanDetails().size());
    }

    @Test
    @DisplayName("Test update loan details")
    void testUpdateLoadDetails() {
        LoanDetailRequestDto dto = createDto(2L, 3L, 22000.00, 11950.00, LocalDate.of(2024,9,23), 2.8F, LocalDate.of(2024,10,23), 0.3F, Boolean.TRUE);
        dto.setId(4L);
        assertDoesNotThrow(()->loanService.addUpdateLoanInfo(dto));
        assertEquals(6,loanDetailsRepository.getAllLoanDetails().size());
        assertEquals(Boolean.TRUE, loanDetailsRepository.getLoanByLoanId(4L).getCancel());
        assertEquals(22000.00, loanDetailsRepository.getLoanByLoanId(4L).getAmount());
    }


    private LoanDetailRequestDto createDto(Long customerId, Long lenderId, Double amount, Double remainingAmount, LocalDate paymentDate, Float interestPerDay, LocalDate dueDate, Float penaltyPerDay, Boolean cancel) {
        LoanDetailRequestDto dto = new LoanDetailRequestDto();
        dto.setCustomerId(customerId);
        dto.setLenderId(lenderId);
        dto.setAmount(amount);
        dto.setRemainingAmount(remainingAmount);
        dto.setPaymentDate(paymentDate);
        dto.setInterestPerDayInPercent(interestPerDay);
        dto.setDueDate(dueDate);
        dto.setPenaltyPerDayInPercent(penaltyPerDay);
        dto.setCancel(cancel);
        return dto;
    }
}
