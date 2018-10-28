package com.pl.mychess.api;

import com.pl.mychess.domain.port.api.UserInterface;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {

    @Override
    public void viewChessboard(Map<String, String> placeAndFigureArrangement, List<String> placesForMove, String currentPlayer) {
        System.out.println("Current player: " + currentPlayer);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String key = String.valueOf((char)('a' + j)) + String.valueOf(8 - i);

                if(placesForMove.contains(key)){
                    System.out.print("{" + placeAndFigureArrangement.get(key) + "}");
                } else {
                    System.out.print("[" + placeAndFigureArrangement.get(key) + "]");
                }
            }

            System.out.println(" " + (8 - i) + ". ");
        }

        for (int i = 0; i < 8; i++) {
            System.out.print(" " + (char)('a' + i) + " ");
        }
        System.out.println("");
    }

    @Override
    public void viewMatchResult(String result) {
        System.out.println(result);
    }

    @Override
    public String getPlaceForMove() {
        boolean correct = false;
        String choice = "";

        while(!correct) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the place [ex. e2], or \"back\", \"resign\": ");
            choice = scanner.nextLine();

            correct = isPlaceCorrect(choice);
        }
        return choice;
    }

    private boolean isPlaceCorrect(String choice) {
        return "back".equals(choice) ||
                "resign".equals(choice) ||
                (choice.length() == 2 &&
                choice.charAt(0) >= 'a' &&
                choice.charAt(0) <= 'h' &&
                choice.charAt(1) >= '1' &&
                choice.charAt(1) <= '8');
    }

    @Override
    public String getFigureForPawnTransform() {
        boolean correct = false;
        String choice = "";

        while(!correct) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the figure for pawn transform [R, N, B, Q]: ");
            choice = scanner.nextLine();
            correct = "R".equals(choice) ||  "N".equals(choice) ||  "B".equals(choice) ||  "Q".equals(choice);
        }
        return choice;
    }
}
