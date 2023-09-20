package com.loan.store.service.helper;


import com.loan.store.common.exception.DataValidationException;
import com.loan.store.dto.request.LoanDetailRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("classpath:mockSql/loan_details.sql")
public class LoanServiceHelperUnitTest {

    @Autowired
    private LoanHelper loanHelper;

    @Test
    @DisplayName("Test when loan details are valid")
    void testWhenLoanDetailsAreValid(){
        LoanDetailRequestDto dto = createDto(1L,1L,10000D,null,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        assertDoesNotThrow(()->
        {
            loanHelper.validateLoanRequest(dto);
        });
    }

    @Test
    @DisplayName("Test when loan details are not valid")
    void testWhenLoanDetailsAreEmptyOrNull(){
        LoanDetailRequestDto dto = createDto(1L,null,10000D,null,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("Lender ID not provided", exception.getMessage());
    }

    @Test
    @DisplayName("Test when loan amount is less then limit")
    void testWhenLoanAmountIsLessThenLimit(){
        LoanDetailRequestDto dto = createDto(1L,1L,100D,null,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("Loan amount must be at least 1000", exception.getMessage());
    }

    @Test
    @DisplayName("Test when interest amount is not valid")
    void testWhenInterestIsNotValid(){
        LoanDetailRequestDto dto1 = createDto(1L,1L,100000D,null,LocalDate.of(2023,7,28), -1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        LoanDetailRequestDto dto2 = createDto(1L,1L,100000D,null,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0F, Boolean.FALSE);

        DataValidationException exception1 = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto1);
        });
        assertEquals("Interest per day must be a positive value", exception1.getMessage());

        DataValidationException exception2 = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto2);
        });
        assertEquals("Penalty per day must be a positive value", exception2.getMessage());
    }

    @Test
    @DisplayName("Test when payment date is after due date")
    void testWhenPaymentDateIsAfterDueDate(){
        LoanDetailRequestDto dto = createDto(1L,1L,10000D,null,LocalDate.of(2023,8,28), 1.0F, LocalDate.of(2023,7,28), 0.1F, Boolean.FALSE);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("Payment Date can not be after Due Date", exception.getMessage());
    }

    @Test
    @DisplayName("Test when remaining amount is given during initial save")
    void testWhenRemainingAmountPresentDuringSave(){
        LoanDetailRequestDto dto = createDto(1L,1L,10000D,1000D,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("Remaining Amount must be null for new loan request", exception.getMessage());
    }

    @Test
    @DisplayName("Test when remaining amount is not valid during update")
    void testWhenRemainingAmountNotPresentDuringUpdate(){
        LoanDetailRequestDto dto = createDto(1L,1L,10000D,-100D,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        dto.setId(1L);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("Remaining Amount must be a positive value for update loan request", exception.getMessage());
    }

    @Test
    @DisplayName("Test when remaining amount is greater then base amount")
    void testWhenRemainingAmountIsGreaterThenAmount(){
        LoanDetailRequestDto dto = createDto(1L,1L,10000D,900000D,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        dto.setId(1L);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("Remaining Amount can not be more then amount", exception.getMessage());
    }

    @Test
    @DisplayName("Test when customer does not exist")
    void testWhenCustomerIdIsInvalid(){
        LoanDetailRequestDto dto = createDto(200L,1L,10000D,null,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("No customer exists with this ID", exception.getMessage());
    }

    @Test
    @DisplayName("Test when lender does not exist")
    void testWhenLenderIdIsInvalid(){
        LoanDetailRequestDto dto = createDto(1L,200L,10000D,null,LocalDate.of(2023,7,28), 1.0F, LocalDate.of(2023,9,28), 0.1F, Boolean.FALSE);
        DataValidationException exception = assertThrows(DataValidationException.class, ()-> {
            loanHelper.validateLoanRequest(dto);
        });
        assertEquals("No lender exists with this ID", exception.getMessage());
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
