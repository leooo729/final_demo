package com.practice.springsecondphrasepractice.advice;

import com.practice.springsecondphrasepractice.controller.dto.response.ActionError;
import com.practice.springsecondphrasepractice.controller.dto.response.ErrorResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.regex.Pattern;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(ParamInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleParamInvalid(
            ParamInvalidException ex
    ) {
        ErrorResponse errorResponse = new ErrorResponse(ActionError.VALIDATE.getMsg(), "");

        for (String errMsg : ex.getErrMessages()) {
            errorResponse.addCheckError(errMsg);
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleDataNotFound(
            DataNotFoundException ex
    ) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ActionError.Query.getMsg(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        ErrorResponse errorResponse = new ErrorResponse(ActionError.VALIDATE.getMsg(), "");

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addFieldError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex
    ) {
        String errMsg = ex.getParameterName() + " ????????????";
        ErrorResponse errorResponse = new ErrorResponse(ActionError.VALIDATE.getMsg(), errMsg);
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex
    ) {
        String errMsg = ex.getMessage().split(":")[1];
        ErrorResponse errorResponse = new ErrorResponse(ActionError.VALIDATE.getMsg(), errMsg);
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex) {
        log.error("Unknown error occurred", ex);
        return ResponseEntity.internalServerError().body(new ErrorResponse(ActionError.SYSTEM.getMsg(), ex.getMessage()));
    }
}
