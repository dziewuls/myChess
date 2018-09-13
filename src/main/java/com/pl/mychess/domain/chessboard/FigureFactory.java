package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.ColorOfFigure;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;

public class FigureFactory {
    public static Figure createFigureByStartedPosition(char coordinateX, int coordinateY) {
        if ((coordinateX == 'a' || coordinateX == 'h') && coordinateY == 1)
            return new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        else if ((coordinateX == 'a' || coordinateX == 'h') && coordinateY == 8)
            return new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        else if ((coordinateX == 'b' || coordinateX == 'g') && coordinateY == 1)
            return new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.WHITE);
        else if ((coordinateX == 'b' || coordinateX == 'g') && coordinateY == 8)
            return new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.BLACK);
        else if ((coordinateX == 'c' || coordinateX == 'f') && coordinateY == 1)
            return new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE);
        else if ((coordinateX == 'c' || coordinateX == 'f') && coordinateY == 8)
            return new Figure(TypeOfFigure.BISHOP, ColorOfFigure.BLACK);
        else if (coordinateX == 'd' && coordinateY == 1)
            return new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE);
        else if (coordinateX == 'd' && coordinateY == 8)
            return new Figure(TypeOfFigure.QUEEN, ColorOfFigure.BLACK);
        else if (coordinateX == 'e' && coordinateY == 1)
            return new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        else if (coordinateX == 'e' && coordinateY == 8)
            return new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);
        else if (coordinateY == 2)
            return new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        else if (coordinateY == 7)
            return new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        else
            return null;
    }

    public static Figure createCopyOfFigure(Figure figureForCopy) {
        if (figureForCopy == null)
            return null;
        else
            return new Figure(figureForCopy);
    }
}
