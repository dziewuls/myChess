package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomMovesValidatorTest {
    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheChosenFigureIsNotAKing() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedPawn);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheRookIsNotInTheCorrectPlace() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('g', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingWasMoved() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        testedKing.setMoved(true);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheRookWasMoved() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        testedRook.setMoved(true);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingIsChecked() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('h', 4, testedBishop);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingStandsInTheCheckedPlace() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('g', 4, testedBlackRook);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingPassesThroughTheCheckedPlace() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('f', 4, testedBlackRook);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenBetweenTheKingAndTheRookAreSomeFigures() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('f', 1, testedBishop);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnTrueWhenCastlingMoveIsCorrect() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);
        Place expectedPlace = new Place('g', 1);

        assertThat(shortCastlingPlace).isEqualTo(expectedPlace);
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheChosenFigureIsNotAKing() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedPawn);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheRookIsNotInTheCorrectPlace() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 2, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingWasMoved() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        testedKing.setMoved(true);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheRookWasMoved() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        testedRook.setMoved(true);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingIsChecked() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('h', 4, testedBishop);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingStandsInTheCheckedPlace() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('c', 4, testedBlackRook);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingPassesThroughTheCheckedPlace() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('d', 4, testedBlackRook);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenBetweenTheKingAndTheRookAreSomeFigures() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('c', 1, testedBishop);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnTrueWhenCastlingMoveIsCorrect() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);

        Place shortCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);
        Place expectedPlace = new Place('c', 1);

        assertThat(shortCastlingPlace).isEqualTo(expectedPlace);
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheChosenFigureIsNotAPawn() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
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

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhiteFigure, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheLastMovedFigureIsNotAPawn() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
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

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheBeatenPawnNotStandBesideTheBeatingPawn() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
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

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheBeatenPawnNotMoveAboutTwoPlacesForward() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createEmptyChessboard();
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

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassandValidatorReturnTrueWhenEnPassantMoveIsCorrect() {
        Chessboard chessboard = (new ClassicChessChessboardFactory()).createInitialChessboard();
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

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);
        Place expectedPlace = new Place('f', 6);

        assertThat(enPassantPlace).isEqualTo(expectedPlace);
    }
}
