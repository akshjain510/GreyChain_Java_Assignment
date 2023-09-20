package com.loan.store.service.helper;

import com.loan.store.common.exception.DataValidationException;
import com.loan.store.dto.request.CustomerDetailRequestDto;
import com.loan.store.entity.CustomerDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CustomerHelper {

    public void validateCustomerRequest(CustomerDetailRequestDto dto){
        if(dto == null){
            throw new DataValidationException("Give proper customer details");
        }
        if(dto.getFullName()==null || dto.getFullName().isBlank()){
            throw new DataValidationException("Name field can't be blank");
        }
        if(dto.getDob()==null || (LocalDate.now().getYear()-dto.getDob().getYear())<=18){
            throw new DataValidationException("Person must be of greater then 18 years");
        }
    }

    public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails, CustomerDetailRequestDto dto){
        customerDetails.setFullName(dto.getFullName());
        customerDetails.setDob(dto.getDob());
        customerDetails.setIncomeType(dto.getIncomeType());
        return customerDetails;
    }
}
