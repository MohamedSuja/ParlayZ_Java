package com.parlayZ.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {

    Object payloadOfGames; //Game dto

    String winningOddsMultiplier;

    String potentialPayout;

    Boolean isVisible;

    Integer noOfWins;

    Integer noOfGames;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    @JsonIgnore
    Double potentialPayoutForGame;
}
