package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovesValidator {
    public static List<Place> getAllPossiblePlacesForTheFigure(Chessboard chessboard, Figure testedFigure) {
        List<Place> result = new ArrayList<>();
        Place placeOfTestedFigure = chessboard.getPlaceForGivenFigure(testedFigure);
        char corX = placeOfTestedFigure.getCoordinateX();
        int corY = placeOfTestedFigure.getCoordinateY();

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
        if (chessboard.getFigureByCoordinates(corX, corY + 1) == null) {
            result.add(chessboard.getPlaceByCoordinates(corX, corY + 1));
        }
        if (!testedFigure.isMoved() && chessboard.getFigureByCoordinates(corX, corY + 2) == null) {
            result.add(chessboard.getPlaceByCoordinates(corX, corY + 2));
        }
        if (isThePlaceExist((char) (corX - 1), corY + 1) &&
                isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX - 1), corY + 1)) {
            result.add(chessboard.getPlaceByCoordinates((char) (corX - 1), corY + 1));
        }
        if (isThePlaceExist((char) (corX + 1), corY + 1) &&
                isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX + 1), corY + 1)) {
            result.add(chessboard.getPlaceByCoordinates((char) (corX + 1), corY + 1));
        }
        return result;
    }

    private static List<Place> getPlacesForPointMovingFigure(Chessboard chessboard, Figure testedFigure, char corX,
                                                             int corY, List<Integer[]> differencesToCurrentPlace) {
        List<Place> result = new ArrayList<>();
        for (Integer[] diff : differencesToCurrentPlace) {
            if (isThePlaceExist((char) (corX + diff[0]), corY + diff[1]) &&
                    (isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX + diff[0]), corY + diff[1])) ||
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

            if (isThePlaceExist((char) (corX + diffX), corY + diffY)) {
                if (isOpponentFigureInPlace(chessboard, testedFigure, (char) (corX + diffX), corY + diffY)) {
                    result.add(chessboard.getPlaceByCoordinates(corX, corY));
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
            differencesToCurrentPlace.remove(0);
        }
        return result;
    }


    private static boolean isOpponentFigureInPlace(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        return chessboard.getFigureByCoordinates(corX, corY) != null &&
                !chessboard.getFigureByCoordinates(corX, corY).getColorOfFigure().equals(testedFigure.getColorOfFigure());
    }

    private static boolean isThePlaceExist(char corX, int corY) {
        return corX >= 'a' && corX <= 'h' && corY >= 1 && corY <= 8;
    }

    public static List<Place> getAllCorrectPlacesForTheFigure(Chessboard chessboard, Figure testedFigure) {
        return null;
    }
}
