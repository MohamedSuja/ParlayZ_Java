package com.parlayZ.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class TicketDetailsDto {
    @NotNull(message = "Number Of Game must not be null ")
    @Positive(message = "Number Of Game should always be positive")
    Integer numberOfGame;


    Double betAmountPerTicket;

    ArrayList<@Valid Game> payLoadOfGame; // payload of Game


}
