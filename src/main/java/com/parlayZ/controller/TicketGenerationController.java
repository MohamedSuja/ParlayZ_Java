package com.parlayZ.controller;

import com.parlayZ.Service.TicketGenerationService;
import com.parlayZ.dto.ResponseDto;
import com.parlayZ.dto.TicketDetailsDto;
import com.parlayZ.util.HttpReqResptils;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("v1/ticketGeneration")
@Slf4j
@Validated
public class TicketGenerationController {

    @Autowired
    TicketGenerationService ticketGeneration;

    @PostMapping("/sendTicketDetails")
    ResponseDto getTicketGeneratedDetails(@RequestBody @Valid TicketDetailsDto ticketDetailsDto, WebRequest webRequest) {
        log.info("TicketGenerationController.getTicketGeneratedDetails() invoked.");
        return ticketGeneration.getTicketGeneratedDetails(ticketDetailsDto, HttpReqResptils.getSearchParameters(webRequest));

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            // Get the index if it's available in the field name

            errors.put("Error Description", errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
