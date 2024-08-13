package com.parlayZ.Service.Impl;

import com.parlayZ.Service.BL.TicketGenerationBL;
import com.parlayZ.Service.Validation.TicketValidation;
import com.parlayZ.constants.ApplicationMessageConstants;
import com.parlayZ.dto.GameResponseDto;
import com.parlayZ.dto.ResponseDto;
import com.parlayZ.dto.TicketDetailsDto;
import com.parlayZ.util.ServiceUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TicketGenerationImplTest {
@InjectMocks
TicketGenerationImpl ticketGenerationimpl;

@Mock
TicketGenerationBL ticketGenerationBL;

    @Mock
    ServiceUtil serviceUtil;

    @Mock
    TicketValidation ticketValidation;
    @Test
    void getTicketGeneratedDetails() {
        Mockito.when(ticketValidation.validationOfTicketGeneration(new TicketDetailsDto(),new HashMap<>())).thenReturn(new ResponseDto());
        ResponseDto ticketGeneratedDetails = ticketGenerationimpl.getTicketGeneratedDetails(new TicketDetailsDto(), new HashMap<>());
        assertNotNull(ticketGeneratedDetails);
    }
    @Test
    void getTicketGeneratedDetails1() {
        Mockito.when(ticketGenerationBL.ticketGeneration(new TicketDetailsDto(),new HashMap<>())).thenReturn(new GameResponseDto());
        Mockito.when(serviceUtil.getServiceResponse(new GameResponseDto())).thenReturn(new ResponseDto());
        ResponseDto ticketGeneratedDetails = ticketGenerationimpl.getTicketGeneratedDetails(new TicketDetailsDto(), new HashMap<>());
        assertNotNull(ticketGeneratedDetails);
    }
    @Test
    void getTicketGeneratedDetails2() {
        Mockito.when(ticketGenerationBL.ticketGeneration(new TicketDetailsDto(),new HashMap<>())).thenReturn(null);
        Mockito.when(serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_TICKET_GENERATION)).thenReturn(new ResponseDto());
        ResponseDto ticketGeneratedDetails = ticketGenerationimpl.getTicketGeneratedDetails(new TicketDetailsDto(), new HashMap<>());
        assertNotNull(ticketGeneratedDetails);
    }
    @Test
    void getTicketGeneratedDetails3() {
        Mockito.when(ticketGenerationBL.ticketGeneration(new TicketDetailsDto(),new HashMap<>())).thenThrow(new RuntimeException());
        Mockito.when(serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_TICKET_GENERATION)).thenReturn(new ResponseDto());
        ResponseDto ticketGeneratedDetails = ticketGenerationimpl.getTicketGeneratedDetails(new TicketDetailsDto(), new HashMap<>());
        assertNotNull(ticketGeneratedDetails);
    }
}