package com.pl.chessboard;

import com.pl.status.Move;
import com.pl.status.TypeOfMove;
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

        Figure expectedFigureForIndexes00 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure expectedFigureForIndexes31 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure expectedFigureForIndexes47 = new Figure(TypeOfFigure.KING, Color.BLACK);

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
                .areExactly(2, f -> f.getTypeOfFigure.equals(TypeOfFigure.ROOK) && f.getColor.equals(Color.WHITE))
                .areExactly(2, f -> f.getTypeOfFigure.equals(TypeOfFigure.KNIGHT) && f.getColor.equals(Color.WHITE))
                .areExactly(2, f -> f.getTypeOfFigure.equals(TypeOfFigure.BISHOP) && f.getColor.equals(Color.WHITE))
                .areExactly(1, f -> f.getTypeOfFigure.equals(TypeOfFigure.QUEEN) && f.getColor.equals(Color.WHITE))
                .areExactly(1, f -> f.getTypeOfFigure.equals(TypeOfFigure.KING) && f.getColor.equals(Color.WHITE))
                .areExactly(8, f -> f.getTypeOfFigure.equals(TypeOfFigure.PAWN) && f.getColor.equals(Color.WHITE))
                .areExactly(2, f -> f.getTypeOfFigure.equals(TypeOfFigure.ROOK) && f.getColor.equals(Color.BLACK))
                .areExactly(2, f -> f.getTypeOfFigure.equals(TypeOfFigure.KNIGHT) && f.getColor.equals(Color.BLACK))
                .areExactly(2, f -> f.getTypeOfFigure.equals(TypeOfFigure.BISHOP) && f.getColor.equals(Color.BLACK))
                .areExactly(1, f -> f.getTypeOfFigure.equals(TypeOfFigure.QUEEN) && f.getColor.equals(Color.BLACK))
                .areExactly(1, f -> f.getTypeOfFigure.equals(TypeOfFigure.KING) && f.getColor.equals(Color.BLACK))
                .areExactly(8, f -> f.getTypeOfFigure.equals(TypeOfFigure.PAWN) && f.getColor.equals(Color.BLACK));
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

        Figure expectedFigureForIndexesA1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure expectedFigureForIndexesD2 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure expectedFigureForIndexesE8 = new Figure(TypeOfFigure.KING, Color.BLACK);

        assertThat(chessboard.getPlaceByCoordinates('a', 1)).isEqualTo(expectedFigureForIndexesA1);
        assertThat(chessboard.getPlaceByCoordinates('d', 2)).isEqualTo(expectedFigureForIndexesD2);
        assertThat(chessboard.getPlaceByCoordinates('e', 8)).isEqualTo(expectedFigureForIndexesE8);
    }

    @Test
    public void shouldUpdateChessboardAfterMove() {
        Chessboard chessboard = new Chessboard();
        Move move = Move.getBuilder()
                .currentPlayerColor(Color.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfMove(TypeOfMove.NORMAL)
                .build();

        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.updateMove(move);

        assertThat(chessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('e', 2)).isNull();
    }

    @Test
    public void shouldUpdateChessboardAfterBackMove() {
        Chessboard chessboard = new Chessboard();
        Move move = Move.getBuilder()
                .currentPlayerColor(Color.WHITE)
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfMove(TypeOfMove.NORMAL)
                .build();

        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        chessboard.updateMove(move);
        chessboard.updateBackMove(move);

        assertThat(chessboard.getFigureByCoordinates('e', 2)).isEqualTo(expectedMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('e', 4)).isNull();
    }
}
