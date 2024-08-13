package com.parlayZ.Service.BL;

import com.parlayZ.controller.TicketGenerationController;
import com.parlayZ.converter.TicketDetailsConverter;
import com.parlayZ.dto.Game;
import com.parlayZ.dto.GameResponseDto;
import com.parlayZ.dto.Ticket;
import com.parlayZ.dto.TicketDetailsDto;
import com.parlayZ.util.GameCombinations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TicketGenerationBLTest {

    @InjectMocks
    TicketGenerationBL ticketGenerationBL;
@Mock
TicketDetailsConverter ticketdetailsConverter;

    @Mock
    GameCombinations gameCombinations;
    @Test
    void ticketGeneration() {
        TicketDetailsDto ticketDetailsDto = new TicketDetailsDto();
        ticketDetailsDto.setNumberOfGame(3);
        ticketDetailsDto.setBetAmountPerTicket(20.00);
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(Game.builder()
                        .home("s")
                        .visitor("V")
                        .gameNo(2)
                         .homeOdds(2.0)
                        .visitorOdds(2.0)
                .build());
        List<String> game = new ArrayList<>();
        List<String> game11 = new ArrayList<>();
        game11.add("s V - W");
        game11.add("s V-W");
        game.add("s V");
        Game game1 = Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .visitorOdds(2.0)
                .homeOdds(2.0)
                .build();
        Mockito.when(ticketdetailsConverter.convert(game1)).thenReturn(Ticket.builder()
                        .HomeOds(2.0)
                        .visitorOds(2.0)
                        .isDeterminedToWin("Visitor")
                        .isVistorSelect(Boolean.TRUE)
                .build());

        ticketDetailsDto.setPayLoadOfGame((ArrayList<Game>) gamesList);
        Mockito.when(gameCombinations.gameCombinations(any(),any())).thenReturn(List.of(game11));
   //     Mockito.when(gameCombinations.gameCombinations(game)).thenReturn(game);
        GameResponseDto gameResponseDto = ticketGenerationBL.ticketGeneration(ticketDetailsDto, new HashMap<>());
        assertNotNull(gameResponseDto);
    }
    @Test
    void ticketGeneration8() {
        TicketDetailsDto ticketDetailsDto = new TicketDetailsDto();
        ticketDetailsDto.setNumberOfGame(3);
        ticketDetailsDto.setBetAmountPerTicket(0.0);
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(2.0)
                .visitorOdds(2.0)
                .build());
        List<String> game = new ArrayList<>();
        List<String> game11 = new ArrayList<>();
        game11.add("s V - W");
        game11.add("s V-W");
        game.add("s V");
        Game game1 = Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .visitorOdds(2.0)
                .homeOdds(2.0)
                .build();
        Mockito.when(ticketdetailsConverter.convert(game1)).thenReturn(Ticket.builder()
                .HomeOds(2.0)
                .visitorOds(2.0)
                .isDeterminedToWin("Visitor")
                .isVistorSelect(Boolean.TRUE)
                .build());

        ticketDetailsDto.setPayLoadOfGame((ArrayList<Game>) gamesList);
        Mockito.when(gameCombinations.gameCombinations(any(),any())).thenReturn(List.of(game11));
        //     Mockito.when(gameCombinations.gameCombinations(game)).thenReturn(game);
        GameResponseDto gameResponseDto = ticketGenerationBL.ticketGeneration(ticketDetailsDto, new HashMap<>());
        assertNotNull(gameResponseDto);
    }
    @Test
    void ticketGeneration1() {
        Map<String, String> searchParams = new HashMap<>();
        searchParams.put("atleast", String.valueOf(0));
        searchParams.put("mypick",String.valueOf(1));
        searchParams.put("americanodds", String.valueOf(true));
        TicketDetailsDto ticketDetailsDto = new TicketDetailsDto();
        ticketDetailsDto.setNumberOfGame(3);
        ticketDetailsDto.setBetAmountPerTicket(20.00);
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(2.0)
                .visitorOdds(2.0)
                .build());
        List<String> game = new ArrayList<>();
        List<String> game11 = new ArrayList<>();
        game11.add("s V - W");
        game11.add("s V-W");
        game.add("s V");
        Game game1 = Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .visitorOdds(2.0)
                .homeOdds(2.0)
                .build();
        Mockito.when(ticketdetailsConverter.convert(game1)).thenReturn(Ticket.builder()
                .HomeOds(2.0)
                .visitorOds(2.0)
                .isDeterminedToWin("Visitor")
                .isVistorSelect(Boolean.TRUE)
                .build());
        ticketDetailsDto.setPayLoadOfGame((ArrayList<Game>) gamesList);
        Mockito.when(gameCombinations.gameCombinations(any(),any())).thenReturn(List.of(game11));
        //     Mockito.when(gameCombinations.gameCombinations(game)).thenReturn(game);
        GameResponseDto gameResponseDto = ticketGenerationBL.ticketGeneration(ticketDetailsDto, searchParams);
        assertNotNull(gameResponseDto);
    }
    @Test
    void ticketGeneration2() {
        Map<String, String> searchParams = new HashMap<>();
        searchParams.put("atleast", String.valueOf(0));
        searchParams.put("mypick",String.valueOf(1));
        searchParams.put("americanodds", String.valueOf(true));
        TicketDetailsDto ticketDetailsDto = new TicketDetailsDto();
        ticketDetailsDto.setNumberOfGame(3);
        ticketDetailsDto.setBetAmountPerTicket(20.00);
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(-2.0)
                .visitorOdds(-2.0)
                .build());
        List<String> game = new ArrayList<>();
        List<String> game11 = new ArrayList<>();
        game11.add("s V - W");
        game11.add("s V-W");
        game.add("s V");
        Game game1 = Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .visitorOdds(-2.0)
                .homeOdds(-2.0)
                .build();
        Mockito.when(ticketdetailsConverter.convert(game1)).thenReturn(Ticket.builder()
                .HomeOds(-2.0)
                .visitorOds(-2.0)
                .isDeterminedToWin("Visitor")
                .isVistorSelect(Boolean.TRUE)
                .build());
        ticketDetailsDto.setPayLoadOfGame((ArrayList<Game>) gamesList);
        Mockito.when(gameCombinations.gameCombinations(any(),any())).thenReturn(List.of(game11));
        //     Mockito.when(gameCombinations.gameCombinations(game)).thenReturn(game);
        GameResponseDto gameResponseDto = ticketGenerationBL.ticketGeneration(ticketDetailsDto, searchParams);
        assertNotNull(gameResponseDto);
    }
    @Test
    void ticketGeneration3() {
        Map<String, String> searchParams = new HashMap<>();
        searchParams.put("atleast", String.valueOf(0));
        searchParams.put("mypick",String.valueOf(1));
        searchParams.put("americanodds", String.valueOf(false));
        TicketDetailsDto ticketDetailsDto = new TicketDetailsDto();
        ticketDetailsDto.setNumberOfGame(3);
        ticketDetailsDto.setBetAmountPerTicket(20.00);
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .homeOdds(-2.0)
                .visitorOdds(2.0)
                .build());
        List<String> game = new ArrayList<>();
        List<String> game11 = new ArrayList<>();
        game11.add("s V - W");
        game11.add("s V-W");
        game.add("s V");
        Game game1 = Game.builder()
                .home("s")
                .visitor("V")
                .gameNo(2)
                .visitorOdds(2.0)
                .homeOdds(-2.0)
                .build();
        Mockito.when(ticketdetailsConverter.convert(game1)).thenReturn(Ticket.builder()
                .HomeOds(-2.0)
                .visitorOds(2.0)
                .isDeterminedToWin("Visitor")
                .isVistorSelect(Boolean.TRUE)
                .build());
        ticketDetailsDto.setPayLoadOfGame((ArrayList<Game>) gamesList);
        Mockito.when(gameCombinations.gameCombinations(any(),any())).thenReturn(List.of(game11));
        //     Mockito.when(gameCombinations.gameCombinations(game)).thenReturn(game);
        GameResponseDto gameResponseDto = ticketGenerationBL.ticketGeneration(ticketDetailsDto, searchParams);
        assertNotNull(gameResponseDto);
    }
}