package com.loan.store.repository;

import com.loan.store.entity.LenderDetails;
import com.loan.store.entity.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LenderDetailsRepository extends JpaRepository<LenderDetails, Long> {

}
