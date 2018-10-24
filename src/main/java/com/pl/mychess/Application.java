package com.pl.mychess;

import com.pl.mychess.api.ConsoleUserInterface;
import com.pl.mychess.domain.classicchess.manager.ClassicChessGameManager;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import com.pl.mychess.domain.port.api.UserInterface;
import com.pl.mychess.domain.port.game.GameManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        GameManager gameManager = new ClassicChessGameManager();
        UserInterface userInterface = new ConsoleUserInterface();
        Map<Place, TypeOfCustomMove> correctMoves = new HashMap<>();
        boolean gameLoop = true;

        displayChessboard(gameManager, userInterface, correctMoves);

        while (gameLoop) {
            String place = userInterface.getPlaceForMove();
            if("back".equals(place)){
                gameManager.backMove();
            }
            else if("resign".equals(place)){
                userInterface.viewMatchResult("game end");
                break;
            }
            else {
                Place placeForMove = new Place(place.charAt(0), Integer.parseInt(String.valueOf(place.charAt(1))));

                if (correctMoves.keySet().contains(placeForMove)) {
                    TypeOfFigure pawnTransformFigure = getTypeOfFigureWhenPawnIsTransform(userInterface, correctMoves.get(placeForMove));
                    gameLoop = makeMoveAndViewResult(gameManager, userInterface, correctMoves, pawnTransformFigure, placeForMove);

                    correctMoves.clear();
                } else {
                    correctMoves = gameManager.getCorrectMoveOptions(placeForMove);
                }
            }
            displayChessboard(gameManager, userInterface, correctMoves);
        }
    }

    private static void displayChessboard(GameManager gameManager, UserInterface userInterface, Map<Place, TypeOfCustomMove> correctMoves) {
        List<String> correctPlaces = mapPlacesToListOfStrings(correctMoves);
        Chessboard chessboard = gameManager.getCurrentChessboard();
        Map<String, String> currentArrangement = getArrangementFromChessboard(chessboard);
        userInterface.viewChessboard(currentArrangement, correctPlaces, gameManager.getColor());
    }

    private static TypeOfFigure getTypeOfFigureWhenPawnIsTransform(UserInterface userInterface, TypeOfCustomMove typeOfCustomMove) {
        TypeOfFigure pawnTransformFigure = null;

        if (typeOfCustomMove == TypeOfCustomMove.PAWN_TRANSFORM) {
            String signPawnTransformFigure = userInterface.getFigureForPawnTransform();
            pawnTransformFigure = TypeOfFigure.getTypeOfFigure(signPawnTransformFigure);
        }
        return pawnTransformFigure;
    }

    private static boolean makeMoveAndViewResult(GameManager gameManager, UserInterface userInterface, Map<Place, TypeOfCustomMove> correctMoves, TypeOfFigure pawnTransformFigure, Place placeForMove) {
        gameManager.makeMove(placeForMove, correctMoves.get(placeForMove), pawnTransformFigure);
        boolean gameLoop = true;

        MatchResult result = gameManager.getGameResult();
        if (result == MatchResult.WHITE_IS_A_WINNER ||
                result == MatchResult.BLACK_IS_A_WINNER ||
                result == MatchResult.DRAW) {
            gameLoop = false;
            userInterface.viewMatchResult(result.toString());
        } else if (result == MatchResult.CHECK) {
            userInterface.viewMatchResult(result.toString());
        }
        return gameLoop;
    }

    private static List<String> mapPlacesToListOfStrings(Map<Place, TypeOfCustomMove> correctMoves) {
        return correctMoves
                .keySet()
                .stream()
                .map(Place::toString)
                .collect(Collectors.toList());
    }

    private static Map<String, String> getArrangementFromChessboard(Chessboard currentChessboard) {
        Map<String, String> result = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Place place = currentChessboard.getPlaceByCoordinates((char) ('a' + j), 1 + i);
                Figure figure = place.getCurrentFigure();
                String sign = " ";
                if (figure != null) {
                    if (figure.getTypeOfFigure() == TypeOfFigure.PAWN) {
                        sign = "P";
                    } else {
                        sign = figure.toString();
                    }
                    if (figure.getColor() == Color.WHITE) {
                        sign = sign.toLowerCase();
                    }
                }

                result.put(place.toString(), sign);
            }
        }

        return result;
    }
}
