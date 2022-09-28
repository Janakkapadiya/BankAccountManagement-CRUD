package com.accountmanagement.practice.AllInOneAcceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

         @RestControllerAdvice
         public class AcceptionHandlerForBadReqs{
         @ResponseStatus(HttpStatus.BAD_REQUEST)
         @ExceptionHandler(MethodArgumentNotValidException.class)
         
              public Map<String,String> handleInvalidArgs(MethodArgumentNotValidException ex)
              {
                     Map<String,String> exception = new HashMap<>();
                     ex.getBindingResult().getFieldErrors().forEach(fieldError -> exception.put(fieldError.getField(), fieldError.getDefaultMessage()));
                     return exception;
              }
  }
