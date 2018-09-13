package com.pl.mychess.domain.model.chessboard;

import com.pl.mychess.domain.chessboard.ChessboardFactory;
import com.pl.mychess.domain.chessboard.StateOfChessboard;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessboardTest {
    //TODO czy zwraca szachownice poprawnie uaktualniona o ruchy specjalne

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

    @Test //TODO dodać test params
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

        // TODO sprobowac wykorzystać lambde
        // assertThat(figuresArrangement)
        //     .isNotNull()
        //     .is(new Condition<>(a -> a.getFigure("a1").equals(new Figure(TypeOfFigure.ROOK,
        //                  ColorOfFigure.WHITE)), "White Rook in a1"))
    }

    @Test //TODO dodać test params
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

    @Test //TODO params
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

    @Test //TODO params
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

        //
//        assertThat(finishedArrangement)
//                .isNotNull()
//                .is(new Condition<>(a -> a.getFigure("e4").equals(new Figure(TypeOfFigure.PAWN,
//                        ColorOfFigure.WHITE)), "White Rook in e4"))
//                .is(new Condition<>(a -> a.getFigure("e5").equals(new Figure(TypeOfFigure.PAWN,
//                        ColorOfFigure.BLACK)), "Black Rook in e5"))
//                .is(new Condition<>(a -> a.getFigure("e2").equals(null), "no figure in e2"))
//                .is(new Condition<>(a -> a.getFigure("e7").equals(null), "no figure in e7"));
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
}
