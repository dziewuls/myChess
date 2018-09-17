package com.pl.mychess.domain.model.chessboard;

import com.pl.mychess.domain.chessboard.ChessboardFactory;
import com.pl.mychess.domain.chessboard.StateOfChessboard;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessboardTest {
    @Test
    public void shouldCreateChessboardWhichConsistsOf8x8Places() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        Place[][] places = chessboard.getPlacesOfChessboard();

        assertThat(places.length).isEqualTo(8);
        assertThat(places[0].length).isEqualTo(8);
    }

    @Test
    public void shouldCreate32ChessFigures() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        List<Figure> figures = chessboard.getFigures();

        assertThat(figures.size()).isEqualTo(32);
    }

    @Test
    public void shouldCreatedPlacesOfChessboardHaveCorrectCoordinatesAndFigures() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        Place[][] places = chessboard.getPlacesOfChessboard();

        Place expectedPlaceForIndexes00 = new Place('a', 1,
                new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE));
        Place expectedPlaceForIndexes23 = new Place('d', 3);
        Place expectedPlaceForIndexes76 = new Place('g', 8,
                new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.BLACK));

        assertThat(places[0][0]).isEqualTo(expectedPlaceForIndexes00);
        assertThat(places[2][3]).isEqualTo(expectedPlaceForIndexes23);
        assertThat(places[7][6]).isEqualTo(expectedPlaceForIndexes76);
    }

    @Test
    public void shouldCreatedFiguresCompatibleWithChessRules() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        List<Figure> figures = chessboard.getFigures();

        assertThat(figures)
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.ROOK,
                        ColorOfFigure.WHITE)), "White Rook"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KNIGHT,
                        ColorOfFigure.WHITE)), "White Knight"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.BISHOP,
                        ColorOfFigure.WHITE)), "White Bishop"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.QUEEN,
                        ColorOfFigure.WHITE)), "White Queen"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KING,
                        ColorOfFigure.WHITE)), "White King"))
                .areExactly(8, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.WHITE)), "White Pawn"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.ROOK,
                        ColorOfFigure.BLACK)), "Black Rook"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KNIGHT,
                        ColorOfFigure.BLACK)), "Black Knight"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.BISHOP,
                        ColorOfFigure.BLACK)), "Black Bishop"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.QUEEN,
                        ColorOfFigure.BLACK)), "Black Queen"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KING,
                        ColorOfFigure.BLACK)), "Black King"))
                .areExactly(8, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.PAWN,
                        ColorOfFigure.BLACK)), "Black Pawn"));
    }

    @Test
    public void shouldReturnCorrectPlaceByCoordinates() {
        Chessboard chessboard = ChessboardFactory.createChessboard();

        Place expectedPlaceForIndexesA1 = new Place('a', 1,
                new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE));
        Place expectedPlaceForIndexesD3 = new Place('d', 3);
        Place expectedPlaceForIndexesG8 = new Place('g', 8,
                new Figure(TypeOfFigure.KNIGHT, ColorOfFigure.BLACK));

        assertThat(chessboard.getPlaceByCoordinates('a', 1)).isEqualTo(expectedPlaceForIndexesA1);
        assertThat(chessboard.getPlaceByCoordinates('d', 3)).isEqualTo(expectedPlaceForIndexesD3);
        assertThat(chessboard.getPlaceByCoordinates('g', 8)).isEqualTo(expectedPlaceForIndexesG8);
    }

    @Test
    public void shouldReturnCorrectFigureByCoordinates() {
        Chessboard chessboard = ChessboardFactory.createChessboard();

        Figure expectedFigureForIndexesA1 = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure expectedFigureForIndexesD2 = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure expectedFigureForIndexesE8 = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);

        assertThat(chessboard.getFigureByCoordinates('a', 1)).isEqualTo(expectedFigureForIndexesA1);
        assertThat(chessboard.getFigureByCoordinates('d', 2)).isEqualTo(expectedFigureForIndexesD2);
        assertThat(chessboard.getFigureByCoordinates('e', 8)).isEqualTo(expectedFigureForIndexesE8);
    }

    @Test
    public void shouldReturnChessboardUpdatedWithMove() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move move = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Chessboard newChessboard = ChessboardFactory.createChessboard(chessboard, move);

        assertThat(newChessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedMovedFigure);
        assertThat(newChessboard.getFigureByCoordinates('e', 2)).isNull();
    }

    @Test
    public void shouldReturnChessboardUpdatedWithListOfMoves() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.BLACK)
                .movedFigure(chessboard.getFigureByCoordinates('e', 7))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());

        Figure expectedWhiteMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure expectedBlackMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);

        Chessboard finishedChessboard = ChessboardFactory.createChessboard(chessboard, moves);

        assertThat(finishedChessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedWhiteMovedFigure);
        assertThat(finishedChessboard.getFigureByCoordinates('e', 2)).isNull();
        assertThat(finishedChessboard.getFigureByCoordinates('e', 5)).isEqualTo(expectedBlackMovedFigure);
        assertThat(finishedChessboard.getFigureByCoordinates('e', 7)).isNull();
    }

    @Test
    public void shouldReturnChessboardUpdatedWithEnPassant() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.BLACK)
                .movedFigure(chessboard.getFigureByCoordinates('f', 7))
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 5))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 5))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .beatenFigure(chessboard.getFigureByCoordinates('f', 5))
                .typeOfCustomMove(TypeOfCustomMove.EN_PASSANT)
                .build());

        Figure expectedWhiteMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure expectedBlackMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);

        Chessboard finishedChessboard = ChessboardFactory.createChessboard(chessboard, moves);

        assertThat(finishedChessboard.getFigureByCoordinates('f', 6)).isEqualTo(expectedWhiteMovedFigure);
        assertThat(finishedChessboard.getFigureByCoordinates('f', 5)).isNull();
        assertThat(finishedChessboard.getFigures()).areExactly(1,
                new Condition<>(f -> f.isBeaten() && f.equals(expectedBlackMovedFigure), "Black beaten pawn"));
    }

    @Test
    public void shouldReturnChessboardUpdatedWithShortCastle() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('g', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('g', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.BLACK)
                .movedFigure(chessboard.getFigureByCoordinates('f', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('f', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('d', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('g', 1))
                .typeOfCustomMove(TypeOfCustomMove.SHORT_CASTLE)
                .build());

        Figure expectedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure expectedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);

        Chessboard finishedChessboard = ChessboardFactory.createChessboard(chessboard, moves);

        assertThat(finishedChessboard.getFigureByCoordinates('g', 1)).isEqualTo(expectedKing);
        assertThat(finishedChessboard.getFigureByCoordinates('f', 1)).isEqualTo(expectedRook);
        assertThat(finishedChessboard.getFigureByCoordinates('h', 1)).isNull();
        assertThat(finishedChessboard.getFigureByCoordinates('e', 1)).isNull();
    }

    @Test
    public void shouldReturnChessboardUpdatedWithLongCastle() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('b', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('b', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('c', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('c', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('c', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('d', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('d', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('d', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('c', 1))
                .typeOfCustomMove(TypeOfCustomMove.LONG_CASTLE)
                .build());

        Figure expectedKing = new Figure(TypeOfFigure.KING, ColorOfFigure.WHITE);
        Figure expectedRook = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);

        Chessboard finishedChessboard = ChessboardFactory.createChessboard(chessboard, moves);

        assertThat(finishedChessboard.getFigureByCoordinates('c', 1)).isEqualTo(expectedKing);
        assertThat(finishedChessboard.getFigureByCoordinates('d', 1)).isEqualTo(expectedRook);
        assertThat(finishedChessboard.getFigureByCoordinates('a', 1)).isNull();
        assertThat(finishedChessboard.getFigureByCoordinates('b', 1)).isNull();
        assertThat(finishedChessboard.getFigureByCoordinates('e', 1)).isNull();
    }

    @Test
    public void shouldReturnChessboardUpdatedWithPawnTransform() {
        Chessboard chessboard = ChessboardFactory.createChessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('a', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('a', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('a', 8))
                .pawnTransformNewFigure(TypeOfFigure.QUEEN)
                .typeOfCustomMove(TypeOfCustomMove.PAWN_TRANSFORM)
                .build());

        Figure expectedQueen = new Figure(TypeOfFigure.QUEEN, ColorOfFigure.WHITE);

        Chessboard finishedChessboard = ChessboardFactory.createChessboard(chessboard, moves);

        assertThat(finishedChessboard.getFigureByCoordinates('a', 8)).isEqualTo(expectedQueen);
        assertThat(finishedChessboard.getFigureByCoordinates('a', 2)).isNull();
    }
}

