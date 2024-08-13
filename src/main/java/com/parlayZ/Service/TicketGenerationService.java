package com.parlayZ.Service;

import com.parlayZ.dto.ResponseDto;
import com.parlayZ.dto.TicketDetailsDto;

import java.util.Map;

public interface TicketGenerationService {
    ResponseDto getTicketGeneratedDetails(TicketDetailsDto ticketDetailsDto, Map<String, String> searchParams);
}
