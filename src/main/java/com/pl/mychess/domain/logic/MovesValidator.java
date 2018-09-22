package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;

import java.util.ArrayList;
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

                break;
            case BISHOP:

                break;
            case ROOK:

                break;
            case QUEEN:

                break;
            case KING:

                break;
        }
        return result;
    }

    private static List<Place>possiblePlacesForPawn(Chessboard chessboard, Figure testedFigure, char corX, int corY) {
        List<Place> result = new ArrayList<>();
        if(chessboard.getFigureByCoordinates(corX, corY + 1) == null){
            result.add(chessboard.getPlaceByCoordinates(corX, corY + 1));
        }
        if(!testedFigure.isMoved() && chessboard.getFigureByCoordinates(corX, corY + 2) == null){
            result.add(chessboard.getPlaceByCoordinates(corX, corY + 2));
        }
        if(isThePlaceExist((char)((int)corX - 1), corY + 1) && chessboard.getFigureByCoordinates((char)((int)corX - 1), corY + 1) != null &&
        !chessboard.getFigureByCoordinates((char)((int)corX - 1), corY + 1).getColorOfFigure().equals(testedFigure.getColorOfFigure())){
            result.add(chessboard.getPlaceByCoordinates((char)((int)corX - 1), corY + 1));
        }
        if(isThePlaceExist((char)((int)corX + 1), corY) && chessboard.getFigureByCoordinates((char)((int)corX + 1), corY) != null &&
        !chessboard.getFigureByCoordinates((char)((int)corX + 1), corY).getColorOfFigure().equals(testedFigure.getColorOfFigure())){
            result.add(chessboard.getPlaceByCoordinates((char)((int)corX + 1), corY + 1));
        }
        return result;
    }

    private static boolean isThePlaceExist(char corX, int corY) {
        return corX >= 'a' && corX <= 'h' && corY >= 1 && corY <= 8;
    }

    public static List<Place> getAllCorrectPlacesForTheFigure(Chessboard chessboard, Figure testedFigure) {
        return null;
    }
}
