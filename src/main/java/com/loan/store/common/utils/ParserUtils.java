package com.loan.store.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.n52.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ParserUtils {

    private final ObjectMapper objectMapper;

    public ParserUtils(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModules(new JtsModule(), new JavaTimeModule());
    }

    public <T> T extractObject(final Object object, final Class<T> type) {
        try {
            return objectMapper.convertValue(object, type);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}