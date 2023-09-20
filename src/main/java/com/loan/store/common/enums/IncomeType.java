package com.loan.store.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.loan.store.common.exception.DataValidationException;

public enum IncomeType {
    SALARIED("SALARIED"),
    SELF_EMPLOYED("SELF_EMPLOYED"),
    BUSINESS("BUSINESS"),
    STUDENT("STUDENT");

    private String value;

    IncomeType(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

    @JsonCreator
    public static IncomeType fromValue(String value){
        for(IncomeType incomeType: values()){
            if(incomeType.getValue().equalsIgnoreCase(value)){
                return incomeType;
            }
        }
        throw new DataValidationException("Invalid Income Type: "+ value);
    }

}
