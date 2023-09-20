package com.loan.store.service;

import com.loan.store.common.exception.DataValidationException;
import com.loan.store.common.utils.ParserUtils;
import com.loan.store.dto.request.CustomerDetailRequestDto;
import com.loan.store.entity.CustomerDetails;
import com.loan.store.repository.CustomerDetailsRepository;
import com.loan.store.service.helper.CustomerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerHelper customerHelper;
    private final CustomerDetailsRepository customerDetailsRepository;
    private final ParserUtils parserUtils;

    public void addUpdateCustomerInfo(CustomerDetailRequestDto dto){
        customerHelper.validateCustomerRequest(dto);
        CustomerDetails customerDetails;
        if(dto.getId()!=null){
            customerDetails = customerDetailsRepository.findById(dto.getId()).orElseThrow(()-> new DataValidationException("No Customer found with this ID"));
            customerDetails = customerHelper.updateCustomerDetails(customerDetails, dto);
        }
        else {
            customerDetails = parserUtils.extractObject(dto, CustomerDetails.class);
        }
        customerDetailsRepository.save(customerDetails);
    }
}
