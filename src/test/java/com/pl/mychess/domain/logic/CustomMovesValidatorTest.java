package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomMovesValidatorTest {
    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenTheChosenFigureIsNotAKing() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedPawn);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenTheRookIsNotInTheCorrectPlace() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('g', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenTheKingWasMoved() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        testedKing.setMoved(true);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenTheRookWasMoved() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        testedRook.setMoved(true);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenTheKingIsChecked() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('h', 4, testedBishop);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenTheKingStandsInTheCheckedPlace() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('g', 4, testedBlackRook);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenTheKingPassesThroughTheCheckedPlace() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('f', 4, testedBlackRook);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnFalseWhenBetweenTheKingAndTheRookAreSomeFigures() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('f', 1, testedBishop);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isFalse();
    }

    @Test
    public void shouldShortCastlingValidatorReturnTrueWhenCastlingMoveIsCorrect() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isTrue();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenTheChosenFigureIsNotAKing() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedPawn);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenTheRookIsNotInTheCorrectPlace() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 2, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenTheKingWasMoved() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        testedKing.setMoved(true);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenTheRookWasMoved() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        testedRook.setMoved(true);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenTheKingIsChecked() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('h', 4, testedBishop);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenTheKingStandsInTheCheckedPlace() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('c', 4, testedBlackRook);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenTheKingPassesThroughTheCheckedPlace() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('d', 4, testedBlackRook);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnFalseWhenBetweenTheKingAndTheRookAreSomeFigures() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('c', 1, testedBishop);

        boolean longCastlingCorrect = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingCorrect).isFalse();
    }

    @Test
    public void shouldLongCastlingValidatorReturnTrueWhenCastlingMoveIsCorrect() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);

        boolean shortCastlingCorrect = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingCorrect).isTrue();
    }

    @Test
    public void shouldEnPassantValidatorReturnFalseWhenTheChosenFigureIsNotAPawn() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedWhiteFigure = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhiteFigure);
        chessboard.setFigureInPlace('f', 5, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .currentPlayerColor(ColorOfFigure.BLACK)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .build();

        boolean enPassantCorrect = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhiteFigure, lastMove);

        assertThat(enPassantCorrect).isFalse();
    }

    @Test
    public void shouldEnPassantValidatorReturnFalseWhenTheLastMovedFigureIsNotAPawn() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure testedBlackFigure = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhitePawn);
        chessboard.setFigureInPlace('f', 5, testedBlackFigure);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackFigure)
                .currentPlayerColor(ColorOfFigure.BLACK)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .build();

        boolean enPassantCorrect = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantCorrect).isFalse();
    }

    @Test
    public void shouldEnPassantValidatorReturnFalseWhenTheBeatenPawnNotStandBesideTheBeatingPawn() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhitePawn);
        chessboard.setFigureInPlace('f', 6, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .currentPlayerColor(ColorOfFigure.BLACK)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .build();

        boolean enPassantCorrect = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantCorrect).isTrue();
    }

    @Test
    public void shouldEnPassantValidatorReturnFalseWhenTheBeatenPawnNotMoveAboutTwoPlacesForward() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhitePawn);
        chessboard.setFigureInPlace('f', 5, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .currentPlayerColor(ColorOfFigure.BLACK)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 6))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .build();

        boolean enPassantCorrect = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantCorrect).isTrue();
    }

    @Test
    public void shouldEnPassandValidatorReturnTrueWhenEnPassantMoveIsCorrect() {
        Chessboard chessboard = ClassicChessChessboardFactory.createEmptyChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhitePawn);
        chessboard.setFigureInPlace('f', 5, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .currentPlayerColor(ColorOfFigure.BLACK)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .build();

        boolean enPassantCorrect = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantCorrect).isTrue();
    }
}
