package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.classicchess.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomMovesValidatorTest {
    private ClassicChessChessboardFactory chessCreator = new ClassicChessChessboardFactory();

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheChosenFigureIsNotAKing() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedPawn);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheRookIsNotInTheCorrectPlace() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('g', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingWasMoved() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        testedKing.setMoved(true);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheRookWasMoved() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        testedRook.setMoved(true);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingIsChecked() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('h', 4, testedBishop);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingStandsInTheCheckedPlace() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('g', 4, testedBlackRook);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenTheKingPassesThroughTheCheckedPlace() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('f', 4, testedBlackRook);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnNullWhenBetweenTheKingAndTheRookAreSomeFigures() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);
        chessboard.setFigureInPlace('f', 1, testedBishop);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);

        assertThat(shortCastlingPlace).isNull();
    }

    @Test
    public void shouldShortCastlingValidatorReturnCorrectPlaceWhenCastlingMoveIsCorrect() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('h', 1, testedRook);

        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedKing);
        Place expectedPlace = new Place('g', 1);

        assertThat(shortCastlingPlace).isEqualTo(expectedPlace);
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheChosenFigureIsNotAKing() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedPawn);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheRookIsNotInTheCorrectPlace() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 2, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingWasMoved() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        testedKing.setMoved(true);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheRookWasMoved() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        testedRook.setMoved(true);
        Figure testedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('e', 2, testedPawn);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingIsChecked() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('h', 4, testedBishop);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingStandsInTheCheckedPlace() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('c', 4, testedBlackRook);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenTheKingPassesThroughTheCheckedPlace() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBlackRook = new Figure(TypeOfFigure.ROOK, Color.BLACK);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('d', 4, testedBlackRook);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnNullWhenBetweenTheKingAndTheRookAreSomeFigures() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure testedBishop = new Figure(TypeOfFigure.BISHOP, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);
        chessboard.setFigureInPlace('c', 1, testedBishop);

        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);

        assertThat(longCastlingPlace).isNull();
    }

    @Test
    public void shouldLongCastlingValidatorReturnTrueWhenCastlingMoveIsCorrect() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        chessboard.setFigureInPlace('e', 1, testedKing);
        chessboard.setFigureInPlace('a', 1, testedRook);

        Place shortCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedKing);
        Place expectedPlace = new Place('c', 1);

        assertThat(shortCastlingPlace).isEqualTo(expectedPlace);
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheChosenFigureIsNotAPawn() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedWhiteFigure = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhiteFigure);
        chessboard.setFigureInPlace('f', 5, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .build();

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhiteFigure, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheLastMovedFigureIsNotAPawn() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure testedBlackFigure = new Figure(TypeOfFigure.BISHOP, Color.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhitePawn);
        chessboard.setFigureInPlace('f', 5, testedBlackFigure);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackFigure)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .build();

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheBeatenPawnNotStandBesideTheBeatingPawn() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhitePawn);
        chessboard.setFigureInPlace('f', 6, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .build();

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassantValidatorReturnNullWhenTheBeatenPawnNotMoveAboutTwoPlacesForward() {
        Chessboard chessboard = chessCreator.createEmptyChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        chessboard.setFigureInPlace('e', 5, testedWhitePawn);
        chessboard.setFigureInPlace('f', 5, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .previousPlace(chessboard.getPlaceByCoordinates('f', 6))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .build();

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);

        assertThat(enPassantPlace).isNull();
    }

    @Test
    public void shouldEnPassandValidatorReturnTrueWhenEnPassantMoveIsCorrect() {
        Chessboard chessboard = chessCreator.createInitialChessboard();
        Figure testedWhitePawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure testedBlackPawn = new Figure(TypeOfFigure.PAWN, Color.BLACK);
        chessboard.setFigureInPlace('c', 5, testedWhitePawn);
        chessboard.setFigureInPlace('b', 5, testedBlackPawn);
        Move lastMove = Move.getMoveBuilder()
                .movedFigure(testedBlackPawn)
                .previousPlace(chessboard.getPlaceByCoordinates('b', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('b', 5))
                .build();

        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedWhitePawn, lastMove);
        Place expectedPlace = new Place('b', 6);

        assertThat(enPassantPlace).isEqualTo(expectedPlace);
    }
}
