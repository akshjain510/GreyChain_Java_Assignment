package com.loan.store.controller;

import com.loan.store.dto.ResponseWrapper;
import com.loan.store.dto.request.LenderDetailRequestDto;
import com.loan.store.service.LenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lender/")
public class LenderController extends ApiRestHandler{
    private final LenderService lenderService;


    @RequestMapping(value = "add-update/", method = RequestMethod.POST)
    public ResponseWrapper addLender(@RequestBody LenderDetailRequestDto lenderDetailRequestDto){
        lenderService.addUpdateLenderInfo(lenderDetailRequestDto);
        if(lenderDetailRequestDto.getId()==null)
        return ResponseWrapper.getSuccessResponse(null, "Lender Added Successfully");
        return ResponseWrapper.getSuccessResponse(null, "Lender Updated Successfully");
    }
}
