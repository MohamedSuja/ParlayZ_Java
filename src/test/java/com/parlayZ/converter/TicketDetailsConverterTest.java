package com.parlayZ.converter;

import com.parlayZ.dto.Game;
import com.parlayZ.dto.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TicketDetailsConverterTest {
@InjectMocks
TicketDetailsConverter ticketDetailsConverter;
    @Test
    void convert() {
        Ticket convert = ticketDetailsConverter.convert(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(2.0)
                .visitorOdds(2.0)
                .build());
        assertNotNull(convert);
    }
}