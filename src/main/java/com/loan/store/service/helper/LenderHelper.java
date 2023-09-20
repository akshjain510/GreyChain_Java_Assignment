package com.loan.store.service.helper;

import com.loan.store.common.exception.DataValidationException;
import com.loan.store.dto.request.LenderDetailRequestDto;
import com.loan.store.entity.LenderDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class LenderHelper {

    public void validateLenderRequest(LenderDetailRequestDto dto){
        if(dto == null){
            throw new DataValidationException("Give proper lender details");
        }
        if(dto.getFullName()==null || dto.getFullName().isBlank()){
            throw new DataValidationException("Name field can't be blank");
        }
        if(dto.getDob()==null || (LocalDate.now().getYear()-dto.getDob().getYear())<=18){
            throw new DataValidationException("Lender must be of greater then 18 years");
        }
    }

    public LenderDetails updateLenderDetails(LenderDetails lenderDetails, LenderDetailRequestDto dto){
        lenderDetails.setFullName(dto.getFullName());
        lenderDetails.setDob(dto.getDob());
        lenderDetails.setRiskProfile(dto.getRiskProfile());
        return lenderDetails;
    }
}
