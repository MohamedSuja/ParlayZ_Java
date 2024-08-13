package com.parlayZ.Service.BL;

import com.parlayZ.converter.TicketDetailsConverter;

import com.parlayZ.dto.*;
import com.parlayZ.util.GameCombinations;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class TicketGenerationBL {

    private final String LEAST_WINS = "atleast";
    private final String NO_OF_GAMES = "mypick";
    @Autowired
    TicketDetailsConverter ticketdetailsConverter;

    @Autowired
    GameCombinations gameCombinations;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public GameResponseDto ticketGeneration(TicketDetailsDto ticketDetailsDto, Map<String, String> searchParams) {
        log.info("TicketGenerationBL.ticketGeneration() invoked.");

        Map<String, String> hashMap = new HashMap<>();
        Double winnningOdds = 0.00;
        List potentialPayout = new ArrayList();
        List<Game> gamesList = null;
        ArrayList<Ticket> ticketList = null;
        Object payLoadOfGame = ticketDetailsDto.getPayLoadOfGame();
        TicketResponseDto ticketResponseDto;
        ArrayList<TicketResponseDto> ticketResponseDtos = new ArrayList<>();
        GameResponseDto gameResponseDto = new GameResponseDto();
        gamesList = (List<Game>) payLoadOfGame;
        List<String> formattedGames = gamesList.stream()
                .map(game -> game.getHome() + " " + game.getVisitor())
                .collect(Collectors.toList());

        List<List<String>> list = gameCombinations.gameCombinations(formattedGames, ticketDetailsDto);
        for (List<String> combination : list) {
            if (ticketDetailsDto.getPayLoadOfGame().size() != 0) {
                winnningOdds = 1.00;
            }
            Integer noOfCombination = 0;

            ticketList = new ArrayList();
            ticketResponseDto = new TicketResponseDto();
            Integer noOfWins = 0;
            for (Game game : gamesList) {
                Ticket gameticket = ticketdetailsConverter.convert(game);
                gameticket.setIsDeterminedToWin("Visitor");
                gameticket.setIsVistorWin(Boolean.TRUE);
                if (combination.get(noOfCombination).equals(game.getHome() + " " + game.getVisitor() + "-W")) {
                    gameticket.setIsDeterminedToWin("Home");
                    gameticket.setIsVistorWin(Boolean.FALSE);
                }
                if (gameticket.getIsDeterminedToWin().equals("Home")) {
                    winnningOdds *= convertOddsToDecimal(game.getHomeOdds(), searchParams);
                }
                if (gameticket.getIsDeterminedToWin().equals("Visitor")) {
                    winnningOdds *= convertOddsToDecimal(game.getVisitorOdds(), searchParams);
                }
                if (gameticket.getIsDeterminedToWin().equals("Visitor")
                        && gameticket.getIsVistorSelect().equals(Boolean.TRUE)) {
                    noOfWins = noOfWins + 1;
                }
                if (gameticket.getIsVistorSelect()) {
                    gameticket.setIsDeterminedToSelect("Visitor");
                } else {
                    gameticket.setIsDeterminedToSelect("Home");
                }
                if (gameticket.getIsDeterminedToWin().equals("Home")
                        && gameticket.getIsVistorSelect().equals(Boolean.FALSE)) {
                    noOfWins = noOfWins + 1;
                }
                noOfCombination = noOfCombination + 1;
                ticketList.add(gameticket);
            }
            if (!((ticketDetailsDto.getPayLoadOfGame()).size() == (ticketDetailsDto.getNumberOfGame()))) {
                while (!((ticketList.size()) == (ticketDetailsDto.getNumberOfGame()))) {
                    ticketList.add(new Ticket());
                }
            }
            ticketResponseDto.setNoOfWins(noOfWins);
            ticketResponseDto.setNoOfGames(gamesList.size());
            ticketResponseDto.setPayloadOfGames(ticketList);
            ticketResponseDto.setWinningOddsMultiplier((decimalFormat.format(winnningOdds)) + "X");
            if (ticketDetailsDto.getBetAmountPerTicket() == 0.0) {
                winnningOdds = 0.0;
            }
            ticketResponseDto.setPotentialPayoutForGame(ticketDetailsDto.getBetAmountPerTicket() * winnningOdds);
            ticketResponseDto.setPotentialPayout(
                    "($" + decimalFormat.format(ticketDetailsDto.getBetAmountPerTicket()) + " BET): " + "$" + "0.00");
            if (ticketDetailsDto.getPayLoadOfGame().size() != 0) {
                ticketResponseDto.setPotentialPayout(
                        "($" + decimalFormat.format(ticketDetailsDto.getBetAmountPerTicket()) + " BET): " + "$"
                                + decimalFormat.format(ticketResponseDto.getPotentialPayoutForGame()));
            }
            potentialPayout.add(ticketResponseDto.getPotentialPayoutForGame());
            ticketResponseDtos.add(ticketResponseDto);
            ticketResponseDto.setIsVisible(Boolean.TRUE);
            gameResponseDto.setPayLoadofTicket(ticketResponseDtos);
        }
        gameResponseDto.setNoOfTicket(list.size());
        gameResponseDto
                .setTotalBetAmount((decimalFormat.format(ticketDetailsDto.getBetAmountPerTicket() * list.size())));
        gameResponseDto.setMaxPayoutAmount("0.00");
        gameResponseDto.setMinPayoutAmount("0.00");
        if (ticketDetailsDto.getPayLoadOfGame().size() != 0) {
            gameResponseDto.setMaxPayoutAmount(decimalFormat.format(Collections.max(potentialPayout)));
            gameResponseDto.setMinPayoutAmount(decimalFormat.format(Collections.min(potentialPayout)));
        }
        if (searchParams.get(NO_OF_GAMES) != null && !searchParams.get(NO_OF_GAMES).isEmpty()) {

            return filterationOfWins(searchParams, ticketResponseDtos, ticketDetailsDto, gameResponseDto);
        }
        return gameResponseDto;
    }

    private GameResponseDto filterationOfWins(Map<String, String> searchParams,
            ArrayList<TicketResponseDto> ticketResponseDtos, TicketDetailsDto ticketDetailsDto,
            GameResponseDto gameResponseDto) {
        Double totalBetAmount = 0.00;
        Double maxPayoutAmount;
        Double minPayoutAmount;
        maxPayoutAmount = 0.00;
        minPayoutAmount = 0.00;
        if (ticketDetailsDto.getBetAmountPerTicket() != 0.0 && ticketDetailsDto.getPayLoadOfGame().size() != 0) {
            maxPayoutAmount = Double.MIN_VALUE;
            minPayoutAmount = Double.MAX_VALUE;
        }

        Integer noOfGame = 0;
        for (TicketResponseDto ticketResponseDtoForFilteration : ticketResponseDtos) {
            if (ticketResponseDtoForFilteration.getNoOfWins() >= Integer.parseInt(searchParams.get(LEAST_WINS))) {
                noOfGame = noOfGame + 1;
                ticketResponseDtoForFilteration.setIsVisible(Boolean.TRUE);
                totalBetAmount += ticketDetailsDto.getBetAmountPerTicket();
                Double potentialPayout = (Double) ticketResponseDtoForFilteration.getPotentialPayoutForGame();
                maxPayoutAmount = Math.max(maxPayoutAmount, potentialPayout);
                minPayoutAmount = Math.min(minPayoutAmount, potentialPayout);
            } else {
                ticketResponseDtoForFilteration.setIsVisible(Boolean.FALSE);
            }
        }
        gameResponseDto.setTotalBetAmount((decimalFormat.format(totalBetAmount)));
        gameResponseDto.setMaxPayoutAmount(decimalFormat.format(maxPayoutAmount));
        gameResponseDto.setMinPayoutAmount(decimalFormat.format(minPayoutAmount));
        gameResponseDto.setNoOfTicket(noOfGame);
        return gameResponseDto;
    }

    private Double convertOddsToDecimal(Double americanOdds, Map<String, String> searchParams) {
        Double negativeAmericanOdds = 0.00;
        if (searchParams.containsKey("americanodds") && searchParams.get("americanodds").equals("true")) {
            if (americanOdds > 0) {
                return 1 + (americanOdds / 100.0);
            } else if (americanOdds < 0) {
                negativeAmericanOdds = -americanOdds;
                return 1 + (100.0 / negativeAmericanOdds);

            } else {
                return 1.0;
            }
        } else {
            return 1.0 * americanOdds;
        }
    }
}
