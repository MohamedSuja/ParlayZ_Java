package com.parlayZ.util;

import com.parlayZ.dto.TicketDetailsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GameCombinations {

    public List gameCombinations(List list, TicketDetailsDto ticketDetailsDto) {
        List<String> games = list;
        if(!ticketDetailsDto.getPayLoadOfGame().equals(ticketDetailsDto.getNumberOfGame())) {
            while (!((list.size())==(ticketDetailsDto.getNumberOfGame()))) {
                list.add("nl nl");
            }
        }
        List<String> parties = Arrays.asList("W", "L");

        return generateCombinations(games, parties);
    }

    public static List<List<String>> generateCombinations(List<String> games, List<String> parties) {
        List<List<String>> combinations = new ArrayList<>();
        generateCombinationsRecursive(games, parties, new ArrayList<>(), combinations, 0);
        return combinations;
    }

    public static void generateCombinationsRecursive(List<String> games, List<String> parties,
                                                     List<String> currentCombination, List<List<String>> combinations, int index) {
        if (index == games.size()) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (String party : parties) {
            currentCombination.add(games.get(index) + "-" + party);
            generateCombinationsRecursive(games, parties, currentCombination, combinations, index + 1);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}