package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateOfGameToolsValidatorTest {
    private ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();

    @Test
    public void shouldIsThePlaceAttackedMethodReturnTrueWhenThePlaceIsAttacked(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure attackingFigure = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 4, attackingFigure);
        Place attackedPlace = chessboard.getPlaceByCoordinates('e', 8);

        boolean result = StateOfGameToolsValidator.isThePlaceAttacked(chessboard, attackedPlace, ColorOfFigure.WHITE);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldIsThePlaceAttackedMethodReturnTrueWhenThePlaceIsAttackedByPawn(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure attackingFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 4, attackingFigure);
        Place attackedPlace = chessboard.getPlaceByCoordinates('f', 3);

        boolean result = StateOfGameToolsValidator.isThePlaceAttacked(chessboard, attackedPlace, ColorOfFigure.WHITE);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldIsThePlaceAttackedMethodReturnFalseWhenThePlaceIsNotAttacked(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure attackingFigure = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        Figure figure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);

        chessboard.setFigureInPlace('e', 4, attackingFigure);
        chessboard.setFigureInPlace('e', 7, figure);
        Place attackedPlace = chessboard.getPlaceByCoordinates('e', 8);

        boolean result = StateOfGameToolsValidator.isThePlaceAttacked(chessboard, attackedPlace, ColorOfFigure.WHITE);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldIsTheFigureAttackedMethodReturnTrueWhenTheFigureIsAttacked(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure attackingFigure = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        Figure attackedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, attackingFigure);
        chessboard.setFigureInPlace('e', 1, attackedFigure);

        boolean result = StateOfGameToolsValidator.isTheFigureAttacked(chessboard, attackedFigure);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldIsTheFigureAttackedMethodReturnTrueWhenTheFigureIsAttackedByPawn(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure attackingFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        Figure attackedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, attackingFigure);
        chessboard.setFigureInPlace('f', 3, attackedFigure);

        boolean result = StateOfGameToolsValidator.isTheFigureAttacked(chessboard, attackedFigure);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldIsTheFigureAttackedMethodReturnFalseWhenTheFigureIsNotAttacked(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure attackingFigure = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        Figure attackedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, attackingFigure);
        chessboard.setFigureInPlace('f', 1, attackedFigure);

        boolean result = StateOfGameToolsValidator.isTheFigureAttacked(chessboard, attackedFigure);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldFindTheKingMethodReturnKingWithGivenColor(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure blackKing = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);
        Figure whiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure somePawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 4, blackKing);
        chessboard.setFigureInPlace('e', 1, whiteKing);
        chessboard.setFigureInPlace('g', 6, somePawn);

        Figure resultKing = StateOfGameToolsValidator.findTheKing(chessboard, ColorOfFigure.WHITE);

        assertThat(resultKing).isEqualTo(whiteKing);
    }

    @Test
    public void shouldHasTheCurrentPlayerAnyCorrectMoveReturnTrueWhenCorrectMoveExist(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure blackKing = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);
        Figure whiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure blackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        Figure whitePawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, blackKing);
        chessboard.setFigureInPlace('e', 4, whiteKing);
        chessboard.setFigureInPlace('h', 4, blackRook);
        chessboard.setFigureInPlace('e', 7, whitePawn);

        boolean result = StateOfGameToolsValidator.hasTheCurrentPlayerAnyCorrectMove(chessboard, ColorOfFigure.WHITE);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldHasTheCurrentPlayerAnyCorrectMoveReturnFalseWhenCorrectMovesNotExist(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure blackKing = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);
        Figure whiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure blackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 3, blackKing);
        chessboard.setFigureInPlace('e', 1, whiteKing);
        chessboard.setFigureInPlace('h', 1, blackRook);

        boolean result = StateOfGameToolsValidator.hasTheCurrentPlayerAnyCorrectMove(chessboard, ColorOfFigure.WHITE);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldIsDrawMethodReturnTrueWhenInChessboardIsInsufficientMaterialForMate(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure blackKing = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);
        Figure whiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure blackKnight = new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.BLACK);
        Figure blackKnight2 = new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.BLACK);
        Figure whiteBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 3, blackKing);
        chessboard.setFigureInPlace('e', 1, whiteKing);
        chessboard.setFigureInPlace('h', 1, blackKnight);
        chessboard.setFigureInPlace('a', 3, blackKnight2);
        chessboard.setFigureInPlace('f', 8, whiteBishop);

        boolean result = StateOfGameToolsValidator.isInsufficientMaterialForMate(chessboard);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldIsDrawMethodReturnFalseWhenInChessboardIsMaterialForMate(){
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Figure blackKing = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);
        Figure whiteKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure blackKnight = new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.BLACK);
        Figure blackKnight2 = new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.WHITE);
        Figure whiteBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 3, blackKing);
        chessboard.setFigureInPlace('e', 1, whiteKing);
        chessboard.setFigureInPlace('h', 1, blackKnight);
        chessboard.setFigureInPlace('a', 3, blackKnight2);
        chessboard.setFigureInPlace('f', 8, whiteBishop);

        boolean result = StateOfGameToolsValidator.isInsufficientMaterialForMate(chessboard);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldIsThePlaceExistMethodReturnTrueWhenThePlaceExist(){
        boolean result = StateOfGameToolsValidator.isThePlaceExist('f', 6);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldIsThePlaceExistMethodReturnFalseWhenThePlaceNotExist(){
        boolean result = StateOfGameToolsValidator.isThePlaceExist('i', 6);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldIsThePlaceExistMethodReturnFalseWhenThePlaceNotExist2(){
        boolean result = StateOfGameToolsValidator.isThePlaceExist('d', 9);

        assertThat(result).isFalse();
    }
}
