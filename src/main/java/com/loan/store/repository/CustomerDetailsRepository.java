package com.loan.store.repository;

import com.loan.store.entity.CustomerDetails;
import com.loan.store.entity.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

}
