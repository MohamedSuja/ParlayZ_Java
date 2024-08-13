package com.parlayZ.converter;

import com.parlayZ.dto.Game;
import com.parlayZ.dto.Ticket;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class TicketDetailsConverter {

    public Ticket convert(Game game) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return Ticket.builder()
                .gameNo(game.getGameNo())
                .HomeTeam(game.getHome())
                .HomeOds(Double.valueOf(decimalFormat.format((game.getHomeOdds()))))
                .vistorTeam(game.getVisitor())
                .visitorOds(Double.valueOf(decimalFormat.format((game.getVisitorOdds()))))
                .isVistorSelect(game.getIsVisitorWin())
                .build();

    }


}
