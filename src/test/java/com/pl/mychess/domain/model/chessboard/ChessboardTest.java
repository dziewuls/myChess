package com.pl.mychess.domain.model.chessboard;

import com.pl.mychess.domain.chessboard.Chessboard;
import com.pl.mychess.domain.chessboard.StateOfChessboard;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ChessboardTest {
    @Test
    public void shouldCreateChessboardWhichConsistsOf8x8Places() {
        Chessboard chessboard = new Chessboard();
        Place[][] places = chessboard.getChessboard();

        assertThat(places.length).isEqualTo(8);
        assertThat(places[0].length).isEqualTo(8);
    }

    @Test
    public void shouldCreatedPlacesOfChessboardHaveCorrectCoordinates() {
        Chessboard chessboard = new Chessboard();
        Place[][] places = chessboard.getChessboard();

        Place expectedPlaceForIndexes00 = new Place('a', 1);
        Place expectedPlaceForIndexes32 = new Place('d', 3);
        Place expectedPlaceForIndexes67 = new Place('g', 8);

        assertThat(places[0][0]).isEqualTo(expectedPlaceForIndexes00);
        assertThat(places[3][2]).isEqualTo(expectedPlaceForIndexes32);
        assertThat(places[6][7]).isEqualTo(expectedPlaceForIndexes67);
    }

    @Test
    public void shouldCreatedPlacesOfChessboardHaveAssignedToCorrectFigure() {
        Chessboard chessboard = new Chessboard();
        Place[][] places = chessboard.getChessboard();

        Figure expectedFigureForIndexes00 = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure expectedFigureForIndexes31 = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure expectedFigureForIndexes47 = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);

        assertThat(places[0][0].getCurrentFigure()).isEqualTo(expectedFigureForIndexes00);
        assertThat(places[3][1].getCurrentFigure()).isEqualTo(expectedFigureForIndexes31);
        assertThat(places[4][7].getCurrentFigure()).isEqualTo(expectedFigureForIndexes47);
    }

    @Test
    public void shouldCreate32ChessFigures() {
        Chessboard chessboard = new Chessboard();
        List<Figure> figures = chessboard.getFigures();

        assertThat(figures.size()).isEqualTo(32);
    }

    @Test
    public void shouldCreatedFiguresCompatibleWithChessRules() {
        Chessboard chessboard = new Chessboard();
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
        Chessboard chessboard = new Chessboard();

        Place expectedPlaceForIndexesA1 = new Place('a', 1);
        Place expectedPlaceForIndexesD3 = new Place('d', 3);
        Place expectedPlaceForIndexesG8 = new Place('g', 8);

        assertThat(chessboard.getPlaceByCoordinates('a', 1)).isEqualTo(expectedPlaceForIndexesA1);
        assertThat(chessboard.getPlaceByCoordinates('d', 3)).isEqualTo(expectedPlaceForIndexesD3);
        assertThat(chessboard.getPlaceByCoordinates('g', 8)).isEqualTo(expectedPlaceForIndexesG8);
    }

    @Test
    public void shouldReturnCorrectFigureByCoordinates() {
        Chessboard chessboard = new Chessboard();

        Figure expectedFigureForIndexesA1 = new Figure(TypeOfFigure.ROOK, ColorOfFigure.WHITE);
        Figure expectedFigureForIndexesD2 = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        Figure expectedFigureForIndexesE8 = new Figure(TypeOfFigure.KING, ColorOfFigure.BLACK);

        assertThat(chessboard.getPlaceByCoordinates('a', 1)).isEqualTo(expectedFigureForIndexesA1);
        assertThat(chessboard.getPlaceByCoordinates('d', 2)).isEqualTo(expectedFigureForIndexesD2);
        assertThat(chessboard.getPlaceByCoordinates('e', 8)).isEqualTo(expectedFigureForIndexesE8);
    }

    @Test
    public void shouldUpdateChessboardAfterMove() {
        Chessboard chessboard = new Chessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move move = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.updateMove(move);

        assertThat(chessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('e', 2)).isNull();
    }

    @Test
    public void shouldUpdateChessboardAfterBackMove() {
        Chessboard chessboard = new Chessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Move move = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        chessboard.updateMove(move);
        chessboard.updateBackMove();

        assertThat(chessboard.getFigureByCoordinates('e', 2)).isEqualTo(expectedMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('e', 4)).isNull();
    }

    @Test
    public void shouldUpdateChessboardByArrangement(){
        Chessboard chessboard = new Chessboard();
        StateOfChessboard stateOfChessboard = new StateOfChessboard();
        Arrangement startedArrangement = stateOfChessboard.getStartedArrangement();
        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);

        Move move = stateOfChessboard.getMoveBuilder()
                .currentPlayerColor(ColorOfFigure.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfMove(TypeOfCustomMove.NORMAL)
                .build();

        Arrangement arrangement = (new Arrangement()).createArrangement(startedArrangement, move);
        chessboard.updateChessboardByArrangement(arrangement);

        assertThat(chessboard.getFigureByCoordinates('e', 2)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedMovedFigure);
    }
}
