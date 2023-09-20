package com.loan.store.service;

import com.loan.store.common.exception.DataValidationException;
import com.loan.store.common.utils.ParserUtils;
import com.loan.store.dto.request.LenderDetailRequestDto;
import com.loan.store.entity.LenderDetails;
import com.loan.store.repository.LenderDetailsRepository;
import com.loan.store.service.helper.LenderHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LenderService {

    private final LenderHelper lenderHelper;
    private final LenderDetailsRepository lenderDetailsRepository;
    private final ParserUtils parserUtils;

    public void addUpdateLenderInfo(LenderDetailRequestDto dto){
        lenderHelper.validateLenderRequest(dto);
        LenderDetails lenderDetails;
        if(dto.getId()!=null){
            lenderDetails = lenderDetailsRepository.findById(dto.getId()).orElseThrow(()-> new DataValidationException("No Customer found with this ID"));
            lenderDetails = lenderHelper.updateLenderDetails(lenderDetails, dto);
        }
        else {
            lenderDetails = parserUtils.extractObject(dto, LenderDetails.class);
        }
        lenderDetailsRepository.save(lenderDetails);
    }
}
