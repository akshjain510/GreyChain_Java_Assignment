package com.loan.store.common.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class LocalDateToZonedDateTime implements AttributeConverter<ZonedDateTime, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return zonedDateTime == null ? null : zonedDateTime.toLocalDate();
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(LocalDate date) {
        return date == null ? null : date.atStartOfDay(ZoneOffset.UTC);
    }
}
