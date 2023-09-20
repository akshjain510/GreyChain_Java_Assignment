package com.loan.store.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.loan.store.common.exception.DataValidationException;

public enum RiskProfile {
    LOW("LOW"),
    MODERATE("MODERATE"),
    HIGH("HIGH");

    private String value;

    RiskProfile(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){return value;}

    @JsonCreator
    public static RiskProfile fromValue(String value){
        for(RiskProfile riskProfile : values()){
            if(riskProfile.getValue().equalsIgnoreCase(value))
                return riskProfile;
        }
        throw new DataValidationException("Invalid Risk Profile: "+value);
    }

}
