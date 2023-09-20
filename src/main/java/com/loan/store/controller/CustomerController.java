package com.loan.store.controller;

import com.loan.store.dto.ResponseWrapper;
import com.loan.store.dto.request.CustomerDetailRequestDto;
import com.loan.store.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/")
public class CustomerController extends ApiRestHandler{
    private final CustomerService customerService;


    @RequestMapping(value = "add-update/", method = RequestMethod.POST)
    public ResponseWrapper addCustomer(@RequestBody CustomerDetailRequestDto customerDetailRequestDto){
        customerService.addUpdateCustomerInfo(customerDetailRequestDto);
        if(customerDetailRequestDto.getId()==null)
        return ResponseWrapper.getSuccessResponse(null, "Customer Added Successfully");
        return ResponseWrapper.getSuccessResponse(null, "Customer Updated Successfully");
    }
}
