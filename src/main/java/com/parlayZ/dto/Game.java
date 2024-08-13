package com.parlayZ.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Game {
    @NotNull(message = "Number Of Game must not be null ")
    @Positive(message = "Number Of Game to be always positive ")
    Integer gameNo;
    @NotBlank(message = "Home must not be blank ")
    String home;
    @NotBlank(message = "Visitor must not be blank ")
    String visitor;
    @NotNull(message = "'isVisitorWin' can't be null")
    Boolean isVisitorWin;
    @NotNull(message = "'isHomeWin' can't be null")
    Boolean isHomeWin;
    @NotNull(message = "Home Odds must not be null")
    @Positive(message = "Home Odds should always be positive")
    @Digits(integer = 10, fraction=2,message = "Payout amount must have up to 2 decimal places")
    Double homeOdds;
    @NotNull(message = "Visitor Odds must not be null")
    @Positive(message = "Visitor Odds should always be positive")
    @Digits(integer = 10, fraction=2,message = "Payout amount must have up to 2 decimal places")
    Double visitorOdds;

}
