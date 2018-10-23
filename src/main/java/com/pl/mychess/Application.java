package com.pl.mychess;

import com.pl.mychess.api.ConsoleUserInterface;
import com.pl.mychess.domain.classicchess.manager.ClassicChessGameManager;
import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;
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
        TypeOfFigure pawnTransformFigure = null;

        while (gameManager.getGameResult() == MatchResult.GAME_IS_NOT_COMPLETED) {
            List<String> correctPlaces = mapPlacesToListOfStrings(correctMoves);
            Chessboard chessboard = gameManager.getCurrentChessboard();
            Map<String, String> currentArrangement = getArrangementFromChessboard(chessboard);
            userInterface.viewChessboard(currentArrangement, correctPlaces);

            String place = userInterface.getPlaceForMove();
            Place placeForMove = new Place(place.charAt(0), Integer.parseInt(String.valueOf(place.charAt(1))));

            if (correctMoves.keySet().contains(placeForMove)) {
                gameManager.makeMove(placeForMove, correctMoves.get(placeForMove), pawnTransformFigure);
                correctMoves.clear();
                pawnTransformFigure = null;

            } else {
                correctMoves = gameManager.getCorrectMoveOptions(placeForMove);

                if (correctMoves.get(placeForMove) == TypeOfCustomMove.PAWN_TRANSFORM) {
                    String signPawnTransformFigure = userInterface.getFigureForPawnTransform();
                    pawnTransformFigure = TypeOfFigure.getTypeOfFigure(signPawnTransformFigure);
                }
            }

        }
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

                if (figure == null)
                    result.put(place.toString(), " ");
                else if (figure.getTypeOfFigure() == TypeOfFigure.PAWN)
                    result.put(place.toString(), "p");
                else
                    result.put(place.toString(), figure.toString());
            }
        }

        return result;
    }
}
