package com.loan.store.repository;

import com.loan.store.dto.response.LoanCustomerAggregateDetailResponseDto;
import com.loan.store.dto.response.LoanDetailResponseDto;
import com.loan.store.dto.response.LoanInterestAggregateDetailResponseDto;
import com.loan.store.dto.response.LoanLenderAggregateDetailResponseDto;
import com.loan.store.entity.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.List;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Long> {

    @Query(value = "SELECT new com.loan.store.dto.response.LoanDetailResponseDto(ld, c, l) FROM LoanDetails ld " +
            "INNER JOIN CustomerDetails c ON c.id = ld.customerId " +
            "INNER JOIN LenderDetails l ON l.id = ld.lenderId ")
    List<LoanDetailResponseDto> getAllLoanDetails();

    @Query(value = "SELECT new com.loan.store.dto.response.LoanDetailResponseDto(ld, c, l) FROM LoanDetails ld " +
            "INNER JOIN CustomerDetails c ON c.id = ld.customerId " +
            "INNER JOIN LenderDetails l ON l.id = ld.lenderId " +
            "WHERE ld.id = :id")
    LoanDetailResponseDto getLoanByLoanId(Long id);

    @Query(value = "SELECT new com.loan.store.dto.response.LoanDetailResponseDto(ld, c, l) FROM LoanDetails ld " +
            "INNER JOIN CustomerDetails c ON c.id = ld.customerId " +
            "INNER JOIN LenderDetails l ON l.id = ld.lenderId " +
            "WHERE ld.customerId = :customerId")
    List<LoanDetailResponseDto> getAllLoansByCustomerId(Long customerId);

    @Query(value = "SELECT new com.loan.store.dto.response.LoanDetailResponseDto(ld, c, l) FROM LoanDetails ld " +
            "INNER JOIN CustomerDetails c ON c.id = ld.customerId " +
            "INNER JOIN LenderDetails l ON l.id = ld.lenderId " +
            "WHERE ld.lenderId = :lenderId")
    List<LoanDetailResponseDto> getAllLoansByLenderId(Long lenderId);

    @Query(value = "SELECT new com.loan.store.dto.response.LoanCustomerAggregateDetailResponseDto(SUM(ld.remainingAmount), CAST(AVG(ld.interestPerDayInPercent) AS BigDecimal), CAST(AVG(ld.penaltyPerDayInPercent) AS BigDecimal), c) FROM LoanDetails ld " +
            "INNER JOIN CustomerDetails c ON c.id = ld.customerId " +
            "GROUP BY ld.customerId")
    List<LoanCustomerAggregateDetailResponseDto> getAggregateLoanDetailsForCustomers();

    @Query(value = "SELECT new com.loan.store.dto.response.LoanLenderAggregateDetailResponseDto(SUM(ld.remainingAmount), CAST(AVG(ld.interestPerDayInPercent) AS BigDecimal), CAST(AVG(ld.penaltyPerDayInPercent) AS BigDecimal), l) FROM LoanDetails ld " +
            "INNER JOIN LenderDetails l ON l.id = ld.lenderId " +
            "GROUP BY ld.lenderId")
    List<LoanLenderAggregateDetailResponseDto> getAggregateLoanDetailsForLenders();

    @Query(value = "SELECT new com.loan.store.dto.response.LoanInterestAggregateDetailResponseDto(SUM(ld.remainingAmount), CAST(AVG(ld.interestPerDayInPercent) AS BigDecimal), CAST(AVG(ld.penaltyPerDayInPercent) AS BigDecimal)) FROM LoanDetails ld " +
            "GROUP BY ld.interestPerDayInPercent")
    List<LoanInterestAggregateDetailResponseDto> getAggregateLoanDetailsByInterest();


}
