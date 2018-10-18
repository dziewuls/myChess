package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassicChessChessboardFactoryTest {
    private ClassicChessChessboardFactory chessboardFactory = new ClassicChessChessboardFactory();

    @Test
    public void shouldEmptyChessboardCreatorCreateChessboardWithoutFigures() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();

        assertThat(chessboard.getFigureByCoordinates('a', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('b', 5)).isNull();
        assertThat(chessboard.getFigureByCoordinates('d', 5)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 8)).isNull();
        assertThat(chessboard.getFigureByCoordinates('f', 7)).isNull();
    }

    @Test
    public void shouldSetGivenFigureInChessboard() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        chessboard.setFigureInPlace('a', 1, new Figure(TypeOfFigure.PAWN, Color.WHITE));
        chessboard.setFigureInPlace('c', 2, new Figure(TypeOfFigure.KING, Color.WHITE));
        chessboard.setFigureInPlace('d', 8, new Figure(TypeOfFigure.ROOK, Color.BLACK));
        chessboard.setFigureInPlace('h', 6, new Figure(TypeOfFigure.KING, Color.BLACK));

        assertThat(chessboard.getFigureByCoordinates('a', 1)).isEqualTo(new Figure(TypeOfFigure.PAWN, Color.WHITE));
        assertThat(chessboard.getFigureByCoordinates('c', 2)).isEqualTo(new Figure(TypeOfFigure.KING, Color.WHITE));
        assertThat(chessboard.getFigureByCoordinates('d', 8)).isEqualTo(new Figure(TypeOfFigure.ROOK, Color.BLACK));
        assertThat(chessboard.getFigureByCoordinates('h', 6)).isEqualTo(new Figure(TypeOfFigure.KING, Color.BLACK));

    }

    @Test
    public void shouldChessboardCreatorCreateChessboardWhichConsistsOf8x8Places() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Place[][] places = chessboard.getPlacesOfChessboard();

        assertThat(places.length).isEqualTo(8);
        assertThat(places[0].length).isEqualTo(8);
    }

    @Test
    public void shouldInitialChessboardCreatorCreate32ChessFigures() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Figure> figures = chessboard.getFigures();

        assertThat(figures.size()).isEqualTo(32);
    }

    @Test
    public void shouldCreatedPlacesOfChessboardHaveCorrectCoordinatesAndFigures() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();
        Place[][] places = chessboard.getPlacesOfChessboard();

        Place expectedPlaceForIndexes00 = new Place('a', 1,
                new Figure(TypeOfFigure.ROOK, Color.WHITE));
        Place expectedPlaceForIndexes23 = new Place('d', 3);
        Place expectedPlaceForIndexes76 = new Place('g', 8,
                new Figure(TypeOfFigure.KNIGHT, Color.BLACK));

        assertThat(places[0][0]).isEqualTo(expectedPlaceForIndexes00);
        assertThat(places[2][3]).isEqualTo(expectedPlaceForIndexes23);
        assertThat(places[7][6]).isEqualTo(expectedPlaceForIndexes76);
    }

    @Test
    public void shouldInitialChessboardCreatorCreatedFiguresCompatibleWithChessRules() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Figure> figures = chessboard.getFigures();

        assertThat(figures)
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.ROOK,
                        Color.WHITE)), "White Rook"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KNIGHT,
                        Color.WHITE)), "White Knight"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.BISHOP,
                        Color.WHITE)), "White Bishop"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.QUEEN,
                        Color.WHITE)), "White Queen"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KING,
                        Color.WHITE)), "White King"))
                .areExactly(8, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.PAWN,
                        Color.WHITE)), "White Pawn"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.ROOK,
                        Color.BLACK)), "Black Rook"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KNIGHT,
                        Color.BLACK)), "Black Knight"))
                .areExactly(2, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.BISHOP,
                        Color.BLACK)), "Black Bishop"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.QUEEN,
                        Color.BLACK)), "Black Queen"))
                .areExactly(1, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.KING,
                        Color.BLACK)), "Black King"))
                .areExactly(8, new Condition<>(f -> f.equals(new Figure(TypeOfFigure.PAWN,
                        Color.BLACK)), "Black Pawn"));
    }

    @Test
    public void shouldChessboardReturnCorrectPlaceByCoordinates() {
        Chessboard chessboard = chessboardFactory.createEmptyChessboard();

        Place expectedPlaceForIndexesA1 = new Place('a', 1,
                new Figure(TypeOfFigure.ROOK, Color.WHITE));
        Place expectedPlaceForIndexesD3 = new Place('d', 3);
        Place expectedPlaceForIndexesG8 = new Place('g', 8,
                new Figure(TypeOfFigure.KNIGHT, Color.BLACK));

        assertThat(chessboard.getPlaceByCoordinates('a', 1)).isEqualTo(expectedPlaceForIndexesA1);
        assertThat(chessboard.getPlaceByCoordinates('d', 3)).isEqualTo(expectedPlaceForIndexesD3);
        assertThat(chessboard.getPlaceByCoordinates('g', 8)).isEqualTo(expectedPlaceForIndexesG8);
    }

    @Test
    public void shouldChessboardReturnCorrectFigureByCoordinates() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();

        Figure expectedFigureForIndexesA1 = new Figure(TypeOfFigure.ROOK, Color.WHITE);
        Figure expectedFigureForIndexesD2 = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure expectedFigureForIndexesE8 = new Figure(TypeOfFigure.KING, Color.BLACK);

        assertThat(chessboard.getFigureByCoordinates('a', 1)).isEqualTo(expectedFigureForIndexesA1);
        assertThat(chessboard.getFigureByCoordinates('d', 2)).isEqualTo(expectedFigureForIndexesD2);
        assertThat(chessboard.getFigureByCoordinates('e', 8)).isEqualTo(expectedFigureForIndexesE8);
    }

    @Test
    public void shouldUpdatedChessboardCreatorReturnChessboardUpdatedWithMove() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        Move move = Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Chessboard newChessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, move);

        assertThat(newChessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedMovedFigure);
        assertThat(newChessboard.getFigureByCoordinates('e', 2)).isNull();
    }

    @Test
    public void shouldReturnChessboardUpdatedWithListOfMoves() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 7))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());

        Figure expectedWhiteMovedFigure = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure expectedBlackMovedFigure = new Figure(TypeOfFigure.PAWN, Color.BLACK);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }

        assertThat(chessboard.getFigureByCoordinates('e', 4)).isEqualTo(expectedWhiteMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('e', 2)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 5)).isEqualTo(expectedBlackMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('e', 7)).isNull();
    }

    @Test
    public void shouldUpdatedChessboardCreatorReturnChessboardUpdatedWithEnPassant() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('f', 7))
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 5))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 5))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .beatenFigure(chessboard.getFigureByCoordinates('f', 5))
                .typeOfCustomMove(TypeOfCustomMove.EN_PASSANT)
                .build());

        Figure expectedWhiteMovedFigure = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure expectedBlackMovedFigure = new Figure(TypeOfFigure.PAWN, Color.BLACK);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }
        assertThat(chessboard.getFigureByCoordinates('f', 6)).isEqualTo(expectedWhiteMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('f', 5)).isNull();
        assertThat(chessboard.getFigures()).areExactly(1,
                new Condition<>(f -> f.isBeaten() && f.equals(expectedBlackMovedFigure), "Black beaten pawn"));
    }

    @Test
    public void shouldUpdatedChessboardCreatorReturnChessboardUpdatedWithShortCastle() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('g', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('g', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('f', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('f', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('d', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('g', 1))
                .typeOfCustomMove(TypeOfCustomMove.SHORT_CASTLE)
                .build());

        Figure expectedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure expectedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }
        assertThat(chessboard.getFigureByCoordinates('g', 1)).isEqualTo(expectedKing);
        assertThat(chessboard.getFigureByCoordinates('f', 1)).isEqualTo(expectedRook);
        assertThat(chessboard.getFigureByCoordinates('h', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 1)).isNull();
    }

    @Test
    public void shouldUpdatedChessboardCreatorReturnChessboardUpdatedWithLongCastle() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('b', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('b', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('c', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('c', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('c', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('d', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('d', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('d', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('c', 1))
                .typeOfCustomMove(TypeOfCustomMove.LONG_CASTLE)
                .build());

        Figure expectedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure expectedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }
        assertThat(chessboard.getFigureByCoordinates('c', 1)).isEqualTo(expectedKing);
        assertThat(chessboard.getFigureByCoordinates('d', 1)).isEqualTo(expectedRook);
        assertThat(chessboard.getFigureByCoordinates('a', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('b', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 1)).isNull();
    }

    @Test
    public void shouldUpdatedChessboardCreatorReturnChessboardUpdatedWithPawnTransform() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('a', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('a', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('a', 8))
                .pawnTransformNewFigure(TypeOfFigure.QUEEN)
                .typeOfCustomMove(TypeOfCustomMove.PAWN_TRANSFORM)
                .build());

        Figure expectedQueen = new Figure(TypeOfFigure.QUEEN, Color.WHITE);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }
        assertThat(chessboard.getFigureByCoordinates('a', 8)).isEqualTo(expectedQueen);
        assertThat(chessboard.getFigureByCoordinates('a', 2)).isNull();
    }

    @Test
    public void shouldBackMoveUpdateReturnChessboardUpdatedWithBackMove() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        Move move = Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 4))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        Figure expectedMovedFigure = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Chessboard updatedChessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, move);
        Chessboard updatedChessboardByBackMove = chessboardFactory.createUpdatedChessboardByBackMove(updatedChessboard, move);

        assertThat(updatedChessboardByBackMove.getFigureByCoordinates('e', 2)).isEqualTo(expectedMovedFigure);
        assertThat(updatedChessboardByBackMove.getFigureByCoordinates('e', 4)).isNull();
    }

    @Test
    public void shouldBackMoveUpdateReturnChessboardUpdatedWithEnPassantBack() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        Move move1 = Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, move1);

        Move move2 = Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('f', 7))
                .previousPlace(chessboard.getPlaceByCoordinates('f', 7))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 5))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build();

        chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, move2);

        Move move3 = Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 5))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 5))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .beatenFigure(chessboard.getFigureByCoordinates('f', 5))
                .typeOfCustomMove(TypeOfCustomMove.EN_PASSANT)
                .build();

        chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, move3);

        Figure expectedWhiteMovedFigure = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure expectedBlackMovedFigure = new Figure(TypeOfFigure.PAWN, Color.BLACK);

        chessboard = chessboardFactory.createUpdatedChessboardByBackMove(chessboard, move3);

        assertThat(chessboard.getFigureByCoordinates('f', 6)).isNull();
        assertThat(chessboard.getFigureByCoordinates('f', 5)).isEqualTo(expectedBlackMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('e', 5)).isEqualTo(expectedWhiteMovedFigure);
        assertThat(chessboard.getFigureByCoordinates('f', 5).isBeaten()).isFalse();
    }

    @Test
    public void shouldBackMoveUpdateReturnChessboardUpdatedWithShortCastleBack() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('g', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('g', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('f', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('f', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('f', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('d', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('g', 1))
                .typeOfCustomMove(TypeOfCustomMove.SHORT_CASTLE)
                .build());

        Figure expectedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure expectedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }

        chessboard = chessboardFactory.createUpdatedChessboardByBackMove(chessboard, moves.get(moves.size() - 1));

        assertThat(chessboard.getFigureByCoordinates('g', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('f', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('h', 1)).isEqualTo(expectedRook);
        assertThat(chessboard.getFigureByCoordinates('e', 1)).isEqualTo(expectedKing);
    }

    @Test
    public void shouldBackMoveUpdateReturnChessboardUpdatedWithLongCastleBack() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('b', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('b', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('c', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('c', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('c', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('e', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('d', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('d', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('d', 6))
                .typeOfCustomMove(TypeOfCustomMove.NORMAL)
                .build());
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('e', 1))
                .previousPlace(chessboard.getPlaceByCoordinates('e', 1))
                .nextPlace(chessboard.getPlaceByCoordinates('c', 1))
                .typeOfCustomMove(TypeOfCustomMove.LONG_CASTLE)
                .build());

        Figure expectedKing = new Figure(TypeOfFigure.KING, Color.WHITE);
        Figure expectedRook = new Figure(TypeOfFigure.ROOK, Color.WHITE);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }

        chessboard = chessboardFactory.createUpdatedChessboardByBackMove(chessboard, moves.get(moves.size() - 1));

        assertThat(chessboard.getFigureByCoordinates('c', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('d', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('a', 1)).isEqualTo(expectedRook);
        assertThat(chessboard.getFigureByCoordinates('b', 1)).isNull();
        assertThat(chessboard.getFigureByCoordinates('e', 1)).isEqualTo(expectedKing);
    }

    @Test
    public void shouldBackMoveUpdateReturnChessboardUpdatedWithPawnTransformBack() {
        Chessboard chessboard = chessboardFactory.createInitialChessboard();
        List<Move> moves = new ArrayList<>();
        moves.add(Move.getMoveBuilder()
                .movedFigure(chessboard.getFigureByCoordinates('a', 2))
                .previousPlace(chessboard.getPlaceByCoordinates('a', 2))
                .nextPlace(chessboard.getPlaceByCoordinates('a', 8))
                .beatenFigure(chessboard.getFigureByCoordinates('a', 8))
                .pawnTransformNewFigure(TypeOfFigure.QUEEN)
                .typeOfCustomMove(TypeOfCustomMove.PAWN_TRANSFORM)
                .build());

        Figure expectedPawn = new Figure(TypeOfFigure.PAWN, Color.WHITE);
        Figure expectedRook = new Figure(TypeOfFigure.ROOK, Color.BLACK);

        for (Move m : moves) {
            chessboard = chessboardFactory.createUpdatedChessboardByMove(chessboard, m);
        }

        chessboard = chessboardFactory.createUpdatedChessboardByBackMove(chessboard, moves.get(moves.size() - 1));

        assertThat(chessboard.getFigureByCoordinates('a', 8)).isEqualTo(expectedRook);
        assertThat(chessboard.getFigureByCoordinates('a', 2)).isEqualTo(expectedPawn);
    }
}

