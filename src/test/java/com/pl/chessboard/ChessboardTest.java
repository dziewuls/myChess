package com.pl.chessboard;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

        Figure expectedPlaceForIndexes00 = new Figure('a', 1);
        Figure expectedPlaceForIndexes32 = new Figure('d', 3);
        Figure expectedPlaceForIndexes67 = new Figure('g', 8);
    }

    @Test
    public void shouldCreate32ChessFigures() {

    }

    @Test
    public void shouldCreatedFiguresCompatibleWithChessRules() {

    }

    @Test
    public void shouldReturnCorrectPlaceByCoordinates() {

    }

    @Test
    public void shouldReturnCorrectFigureByCoordinates() {

    }
}
