package com.parlayZ.Service.Validation;

import com.parlayZ.dto.Game;
import com.parlayZ.dto.ResponseDto;
import com.parlayZ.dto.TicketDetailsDto;
import com.parlayZ.util.ServiceUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TicketValidationTest {

    @InjectMocks
    TicketValidation ticketValidation;

    @Mock
    ServiceUtil serviceUtil;

    @ParameterizedTest
    @CsvSource({
            "1,3,true",
            "3,3,true",
            "3,1,true",
            "3,3,false",
            "1,3,false",
            "13,3,false",
    })
    void validationOfTicketGenerationParameterized(int numberOfGame, int myPick, boolean americanOdds) {
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(-10000.000)
                .visitorOdds(11.000)
                .build());
        gamesList.add(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(-2.0)
                .visitorOdds(2.0)
                .build());
        gamesList.add(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(-2.0)
                .visitorOdds(2.0)
                .build());

        TicketDetailsDto ticketDetailsDto = new TicketDetailsDto();
        ticketDetailsDto.setNumberOfGame(numberOfGame);
        Map<String, String> searchParams = new HashMap<>();
        searchParams.put("atleast", String.valueOf(1));
        searchParams.put("mypick", String.valueOf(myPick));
        ticketDetailsDto.setPayLoadOfGame((ArrayList<Game>) gamesList);
        searchParams.put("americanodds", String.valueOf(americanOdds));

        ResponseDto responseDto = ticketValidation.validationOfTicketGeneration(ticketDetailsDto, searchParams);
        assertNull(responseDto);
    }
}

