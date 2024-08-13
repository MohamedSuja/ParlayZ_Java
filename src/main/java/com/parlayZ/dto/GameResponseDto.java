package com.parlayZ.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseDto {
    Integer noOfTicket;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    Object totalBetAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    Object maxPayoutAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    Object minPayoutAmount;

    Object payLoadofTicket;  // Ticket ResponseDto


}
