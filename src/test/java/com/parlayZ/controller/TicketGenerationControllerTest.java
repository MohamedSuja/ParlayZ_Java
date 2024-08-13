package com.parlayZ.controller;

import com.parlayZ.Service.TicketGenerationService;
import com.parlayZ.dto.ResponseDto;
import com.parlayZ.dto.TicketDetailsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import com.parlayZ.util.HttpReqResptils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TicketGenerationControllerTest {


    @InjectMocks
    TicketGenerationController ticketGenerationController;

    @Mock
    TicketGenerationService ticketGenerationService;
    @Test
    void getTicketGeneratedDetails() {
        Integer pageNumber =7;
        Integer pageSize =47;
        WebRequest webRequest = mock(WebRequest.class);
        Mockito.when(ticketGenerationService.getTicketGeneratedDetails(new TicketDetailsDto(),HttpReqResptils.getSearchParameters(webRequest))).thenReturn(new ResponseDto());
        ResponseDto ticketGeneratedDetails = ticketGenerationController.getTicketGeneratedDetails(new TicketDetailsDto(), webRequest);
        assertNotNull(ticketGeneratedDetails);

    }
}