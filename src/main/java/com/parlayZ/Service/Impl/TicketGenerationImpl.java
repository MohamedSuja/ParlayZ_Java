package com.parlayZ.Service.Impl;

import com.parlayZ.Service.BL.TicketGenerationBL;
import com.parlayZ.Service.TicketGenerationService;
import com.parlayZ.Service.Validation.TicketValidation;
import com.parlayZ.constants.ApplicationMessageConstants;
import com.parlayZ.dto.GameResponseDto;
import com.parlayZ.dto.ResponseDto;
import com.parlayZ.dto.TicketDetailsDto;
import com.parlayZ.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TicketGenerationImpl implements TicketGenerationService {

    @Autowired
    TicketGenerationBL ticketGenerationBL;

    @Autowired
    ServiceUtil serviceUtil;

    @Autowired
    TicketValidation ticketValidation;


    @Override
    public ResponseDto getTicketGeneratedDetails(TicketDetailsDto ticketDetailsDto, Map<String, String> searchParams) {
        ResponseDto responseDto = null;
        ResponseDto validationOfTicketGeneration = ticketValidation.validationOfTicketGeneration(ticketDetailsDto, searchParams);
        if(validationOfTicketGeneration!=null){
            return validationOfTicketGeneration;
        }
        else {
            try {
                log.info("TicketGenerationImpl.getTicketGeneratedDetails() invoked.");
                GameResponseDto ticketResponseDto = ticketGenerationBL.ticketGeneration(ticketDetailsDto, searchParams);
                if (ticketResponseDto != null) {
                    return serviceUtil.getServiceResponse(ticketResponseDto);
                } else {
                    return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_TICKET_GENERATION);
                }

            } catch (Exception e) {
                return serviceUtil.getExceptionServiceResponseByProperties(
                        ApplicationMessageConstants.ServiceErrorMessages.EX_TICKET_GENERATION);
            }
        }
    }
}
