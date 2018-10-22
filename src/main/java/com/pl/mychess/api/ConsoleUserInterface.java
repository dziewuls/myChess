package com.pl.mychess.api;

import com.pl.mychess.domain.port.api.UserInterface;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {
    @Override
    public void viewChessboard(Map<String, String> chessboard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String key = String.valueOf('a' + j) + String.valueOf(8 - i);
                System.out.print("[" + chessboard.get(key) + "]");
            }

            System.out.println(" " + (8 - i) + ". ");
        }

        for (int i = 0; i < 8; i++) {
            System.out.println('a' + i);
        }
    }

    @Override
    public void viewCorrectMoves(Map<String, String> chessboard, List<String> places) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String key = String.valueOf('a' + j) + String.valueOf(8 - i);

                if(places.contains(key)){
                    System.out.print("{" + chessboard.get(key) + "}");
                } else {
                    System.out.print("[" + chessboard.get(key) + "]");
                }
            }

            System.out.println(" " + (8 - i) + ". ");
        }

        for (int i = 0; i < 8; i++) {
            System.out.println('a' + i);
        }
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
            System.out.println("Enter the place [ex. e2]: ");
            choice = scanner.nextLine();

            correct = isPlaceCorrect(choice);
        }
        return choice;
    }

    private boolean isPlaceCorrect(String choice) {
        return choice.length() == 2 &&
                choice.charAt(0) >= 'a' &&
                choice.charAt(0) <= 'h' &&
                choice.charAt(1) >= '1' &&
                choice.charAt(1) <= '8';
    }

    @Override
    public String getFigureForPawnTransform() {
        boolean correct = false;
        String choice = "";

        while(!correct) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the figure for pawn transform [R, N, B, Q]: ");
            choice = scanner.nextLine();
            correct = choice.equals("R") ||  choice.equals("N") ||  choice.equals("B") ||  choice.equals("Q");
        }
        return choice;
    }
}
