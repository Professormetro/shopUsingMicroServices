package org.chernov.catalogueservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class BadRequestControllerAdvice {

    private final MessageSource messageSource;

    public BadRequestControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindingResult bindingResult, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, messageSource.getMessage("errors.400.title", new Object[0], "errors.400.title", locale));

        problemDetail.setProperty("errors", bindingResult.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));

        return ResponseEntity.badRequest()
                .body(problemDetail);


    }

}
