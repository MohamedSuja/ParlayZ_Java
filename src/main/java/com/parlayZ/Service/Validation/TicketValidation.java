package com.parlayZ.Service.Validation;

import com.parlayZ.dto.Game;
import com.parlayZ.dto.ResponseDto;
import com.parlayZ.dto.TicketDetailsDto;
import com.parlayZ.util.ServiceUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;


@Slf4j
@Component
public class TicketValidation {
    private final String LEAST_WINS = "atleast";
    private final String NO_OF_GAMES = "mypick";
    @Autowired
    ServiceUtil serviceUtil;

    public ResponseDto validationOfTicketGeneration(TicketDetailsDto ticketDetailsDto, Map<String, String> searchParams){

        if(!searchParams.isEmpty()) {
            log.info("TicketValidation.validationOfTicketGeneration() invoked.");
//            if (!ticketDetailsDto.getNumberOfGame().equals(Integer.parseInt(searchParams.get(NO_OF_GAMES)))) {
//                return serviceUtil.getValidationErrorResponse("The no of games mentioned in the filtration  and the amount of games should be equals");
//            }
            if(searchParamValidation(searchParams)!=null){
                return searchParamValidation(searchParams);
            }
        }
        if(ticketDetailsValidation(ticketDetailsDto,searchParams)!=null){
            return ticketDetailsValidation(ticketDetailsDto,searchParams);
        }
        return null;
    }

    private ResponseDto searchParamValidation(Map<String, String> searchParams){
        if(searchParams.get(NO_OF_GAMES)!=null) {
            log.info("TicketValidation.searchParamValidation() invoked.");
            if (Integer.parseInt(searchParams.get(NO_OF_GAMES)) <= 0) {
                return serviceUtil.getValidationErrorResponse("the least winning amount needs to be positive");
            }
            if (Integer.parseInt(searchParams.get(LEAST_WINS)) <= 0) {
                return serviceUtil.getValidationErrorResponse("the number of games needs to be positive");
            }
            if ((Integer.parseInt(searchParams.get(NO_OF_GAMES)) <= Integer.parseInt(searchParams.get(LEAST_WINS)))) {
                return serviceUtil.getValidationErrorResponse("the least winning amount needs to less than the number and both can't be Equal");
            }
        }
       return null;
    }
    private ResponseDto ticketDetailsValidation(TicketDetailsDto ticketDetailsDto,Map<String, String> searchParams) {
        log.info("TicketValidation.ticketDetailsValidation() invoked.");
        Integer gameNo = 0;
        ArrayList<Game> payLoadOfGame = ticketDetailsDto.getPayLoadOfGame();

        if (!(ticketDetailsDto.getNumberOfGame() >= 3)) {
            return serviceUtil.getValidationErrorResponse("the number of games should be more than 3");
        }
        if (!(ticketDetailsDto.getNumberOfGame() <= 8)) {
            return serviceUtil.getValidationErrorResponse("the number of games should be less than 8");
        }
        for (Game game : payLoadOfGame) {
            gameNo = gameNo + 1;

            if (game.getVisitor().equals(game.getHome())) {
                return serviceUtil.getValidationErrorResponse("In the game no " + gameNo + ",Visitor team and Home are the same.");
            }
            if (searchParams.get("americanodds").equals("false")) {

                if (game.getVisitorOdds() <= 0 || game.getVisitorOdds() >= 100) {
                    return serviceUtil.getValidationErrorResponse("In the game " + gameNo + ",The visitor odds needs to be between 0.01 and 99.99");
                }
                if (game.getHomeOdds() <= 0 || game.getHomeOdds() >= 100) {
                    return serviceUtil.getValidationErrorResponse("In the game " + gameNo + ",The Home odds needs to be between 0.01 and 99.99");
                }
            }

        }
            return null;

    }

}
