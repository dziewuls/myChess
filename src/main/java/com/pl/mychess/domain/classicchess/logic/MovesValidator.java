package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Color;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MovesValidator {
    private MovesValidator() {
    }

    static List<Place> getAllPossiblePlacesForTheFigure(Chessboard chessboard, Place placeOfTestedFigure) {
        List<Place> result = new ArrayList<>();
        char corX = placeOfTestedFigure.getCoordinateX();
        int corY = placeOfTestedFigure.getCoordinateY();
        Figure testedFigure = chessboard.getFigureByCoordinates(corX, corY);

        if(testedFigure == null) return result;

        switch (testedFigure.getTypeOfFigure()) {
            case PAWN:
                result = possiblePlacesForPawn(chessboard, testedFigure, corX, corY);
                break;
            case KNIGHT:
                result = possiblePlacesForKnight(chessboard, testedFigure, corX, corY);
                break;
            case BISHOP:
                result = possiblePlacesForBishop(chessboard, testedFigure, corX, corY);
                break;
            case ROOK:
                result = possiblePlacesForRook(chessboard, testedFigure, corX, corY);
                break;
            case QUEEN:
                result = possiblePlacesForQueen(chessboard, testedFigure, corX, corY);
                break;
            case KING:
                result = possiblePlacesForKing(chessboard, testedFigure, corX, corY);
                break;
        }
        return result;
    }

    private static List<Place> possiblePlacesForQueen(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        List<Integer[]> differencesToCurrentPlace = new ArrayList<>(Arrays.asList(
                new Integer[]{1, 1}, new Integer[]{1, -1}, new Integer[]{-1, 1}, new Integer[]{-1, -1},
                new Integer[]{0, 1}, new Integer[]{0, -1}, new Integer[]{1, 0}, new Integer[]{-1, 0}
        ));

        return getPlacesForLineMovingFigure(chessboard, testedFigure, corX, corY, differencesToCurrentPlace);
    }

    private static List<Place> possiblePlacesForRook(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        List<Integer[]> differencesToCurrentPlace = new ArrayList<>(Arrays.asList(
                new Integer[]{0, 1}, new Integer[]{0, -1}, new Integer[]{1, 0}, new Integer[]{-1, 0}
        ));

        return getPlacesForLineMovingFigure(chessboard, testedFigure, corX, corY, differencesToCurrentPlace);
    }

    private static List<Place> possiblePlacesForBishop(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        List<Integer[]> differencesToCurrentPlace = new ArrayList<>(Arrays.asList(
                new Integer[]{1, 1}, new Integer[]{1, -1}, new Integer[]{-1, 1}, new Integer[]{-1, -1}
        ));

        return getPlacesForLineMovingFigure(chessboard, testedFigure, corX, corY, differencesToCurrentPlace);
    }

    private static List<Place> possiblePlacesForKing(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        List<Integer[]> differencesToCurrentPlace = new ArrayList<>(Arrays.asList(
                new Integer[]{1, 1}, new Integer[]{1, -1}, new Integer[]{-1, 1}, new Integer[]{-1, -1},
                new Integer[]{0, 1}, new Integer[]{0, -1}, new Integer[]{1, 0}, new Integer[]{-1, 0}
        ));

        return getPlacesForPointMovingFigure(chessboard, testedFigure, corX, corY, differencesToCurrentPlace);
    }


    private static List<Place> possiblePlacesForKnight(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        List<Integer[]> differencesToCurrentPlace = new ArrayList<>(Arrays.asList(
                new Integer[]{1, 2}, new Integer[]{2, 1}, new Integer[]{1, -2}, new Integer[]{-2, 1},
                new Integer[]{2, -1}, new Integer[]{-1, 2}, new Integer[]{-2, -1}, new Integer[]{-1, -2}
        ));

        return getPlacesForPointMovingFigure(chessboard, testedFigure, corX, corY, differencesToCurrentPlace);
    }

    private static List<Place> possiblePlacesForPawn(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        List<Place> result = new ArrayList<>();
        int direction = (testedFigure.getColor() == Color.WHITE) ? 1 : -1;

        if (StateOfGameToolsValidator.isThePlaceExist(corX, corY + direction) &&
                chessboard.getFigureByCoordinates(corX, corY + direction) == null) {
            result.add(chessboard.getPlaceByCoordinates(corX, corY + direction));
        }
        if (StateOfGameToolsValidator.isThePlaceExist(corX, corY + 2 * direction) &&
                chessboard.getFigureByCoordinates(corX, corY + 2 * direction) == null &&
                chessboard.getFigureByCoordinates(corX, corY + direction) == null &&
                !testedFigure.isMoved()) {
            result.add(chessboard.getPlaceByCoordinates(corX, corY + 2 * direction));
        }
        if (StateOfGameToolsValidator.isThePlaceExist((char) (corX - 1), corY + direction) &&
                isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX - 1), corY + direction)) {
            result.add(chessboard.getPlaceByCoordinates((char) (corX - 1), corY + direction));
        }
        if (StateOfGameToolsValidator.isThePlaceExist((char) (corX + 1), corY + direction) &&
                isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX + 1), corY + direction)) {
            result.add(chessboard.getPlaceByCoordinates((char) (corX + 1), corY + direction));
        }
        return result;
    }

    private static List<Place> getPlacesForPointMovingFigure(Chessboard chessboard, Figure testedFigure, char corX,
                                                             int corY, List<Integer[]> differencesToCurrentPlace) {
        List<Place> result = new ArrayList<>();
        for (Integer[] diff : differencesToCurrentPlace) {
            if (!StateOfGameToolsValidator.isThePlaceExist((char) (corX + diff[0]), corY + diff[1]))
                continue;

            if (isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX + diff[0]), corY + diff[1]) ||
                    chessboard.getFigureByCoordinates((char) (corX + diff[0]), corY + diff[1]) == null) {
                result.add(chessboard.getPlaceByCoordinates((char) (corX + diff[0]), corY + diff[1]));
            }
        }
        return result;
    }

    private static List<Place> getPlacesForLineMovingFigure(Chessboard chessboard, Figure testedFigure, char corX,
                                                            int corY, List<Integer[]> differencesToCurrentPlace) {
        List<Place> result = new ArrayList<>();

        while (!differencesToCurrentPlace.isEmpty()) {
            int diffX = differencesToCurrentPlace.get(0)[0];
            int diffY = differencesToCurrentPlace.get(0)[1];

            if (StateOfGameToolsValidator.isThePlaceExist((char) (corX + diffX), corY + diffY)) {
                addPlaceWhenIsCorrect(chessboard, testedFigure, corX, corY, differencesToCurrentPlace, result);
            }
            differencesToCurrentPlace.remove(0);
        }
        return result;
    }

    private static void addPlaceWhenIsCorrect(Chessboard chessboard, Figure testedFigure, char corX, int corY,
                                              List<Integer[]> differencesToCurrentPlace, List<Place> result) {
        int diffX = differencesToCurrentPlace.get(0)[0];
        int diffY = differencesToCurrentPlace.get(0)[1];

        if (isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX + diffX), corY + diffY)) {
            result.add(chessboard.getPlaceByCoordinates((char) (corX + diffX), corY + diffY));
        } else if (chessboard.getFigureByCoordinates((char) (corX + diffX), corY + diffY) == null) {
            result.add(chessboard.getPlaceByCoordinates((char) (corX + diffX), corY + diffY));

            int newDiffX;
            if (diffX == 0) newDiffX = 0;
            else newDiffX = (diffX < 1) ? diffX - 1 : diffX + 1;

            int newDiffY;
            if (diffY == 0) newDiffY = 0;
            else newDiffY = (diffY < 1) ? diffY - 1 : diffY + 1;

            differencesToCurrentPlace.add(new Integer[]{newDiffX, newDiffY});
        }
    }

    private static boolean isOpponentFigureInPlace(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        return chessboard.getFigureByCoordinates(corX, corY) != null &&
                !chessboard.getFigureByCoordinates(corX, corY).getColor().equals(testedFigure.getColor());
    }
}
