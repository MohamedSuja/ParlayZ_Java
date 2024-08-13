package com.parlayZ.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Ticket {


    Integer gameNo;

    String vistorTeam;

    Double visitorOds;

    Boolean isVistorWin;

    Boolean isVistorSelect;

    String HomeTeam;

    Double HomeOds;

    String isDeterminedToWin;

    String isDeterminedToSelect;

}
