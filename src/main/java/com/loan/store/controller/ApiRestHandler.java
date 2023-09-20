package com.loan.store.controller;


import com.loan.store.common.constants.ResponseErrorCode;
import com.loan.store.common.exception.DataValidationException;
import com.loan.store.dto.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.MethodNotAllowedException;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * This class is meant to be extended by all REST resource "controllers".
 * It contains exception mapping and other common REST API functionality
 **/

@ControllerAdvice(annotations = RestController.class)
public abstract class ApiRestHandler implements ApplicationEventPublisherAware {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException.class)
    public @ResponseBody
    ResponseWrapper handleDataStoreException(DataValidationException ex, WebRequest request, HttpServletResponse response) {
        log.info("Data Validation Exception : " + ex.getMessage());
        return new ResponseWrapper(false, ResponseErrorCode.BAD_DATA_FORMAT, ex.getMessage(), ex.getData());
    }

}